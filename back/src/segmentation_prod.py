import torch
from .model import UNet
from .processing_segmentation import get_size_and_coords, jp2_to_png, split_image, transform_contours_to_geo, transform_utm_to_latlon, contours_to_geojson
from PIL import Image
import torchvision.transforms as transforms
import numpy as np
from pyproj import Transformer
import cv2
import os


def segment_with_coordinates(path_to_jp2, output_path):
    jp2_to_png(path_to_jp2, 'temp_files/output_image_temp.png')

    n, k = split_image('temp_files/output_image_temp.png', 'temp_files/tiles_temp', 128)

    current_directory = os.getcwd()
    print("current_directory", current_directory)

    device = torch.device('cpu')
    print("Использую устройство:", device)

    unet = UNet().to(device)

    unet.load_state_dict(torch.load('models/unet_trained.model', map_location=torch.device('cpu')))
    unet.eval()

    rows = []

    for i in range(n):
        row = []
        for j in range(k):
            image_path = f'temp_files/tiles_temp/tile_{i}_{j}.png'
            pil_image= Image.open(image_path)
            transform = transforms.ToTensor()
            tensor_image = transform(pil_image)
            row.append(tensor_image)


        row_batch = torch.tensor(np.array(row)).float().to(device)

        processed_row = unet(row_batch).detach().cpu()
        processed_row = processed_row.squeeze(1)  # теперь тензор имеет размер 82 x 128 x 128

        # Переставляем тензор так, чтобы получить 82 * 128 в одну линию
        processed_row = processed_row.permute(1, 0, 2)  # теперь тензор имеет размер 128 x 82 x 128

        # Объединяем последние два измерения в одно
        processed_row = processed_row.reshape(128, -1)  # теперь тензор имеет размер 128 x 10496

        rows.append(processed_row)

    

    rows = torch.cat(rows)

    big_image_np = (rows > 0.9).float().cpu().numpy()

    
    np_image = big_image_np.astype(np.uint8) * 255  # Преобразование значений 0 и 1 в 0 и 255

    contours, hierarchy = cv2.findContours(image=np_image, mode=cv2.RETR_TREE, method=cv2.CHAIN_APPROX_NONE)

    good_contours = [i for i in contours if len(i) > 150 and len(i) < 20000]

    origin_x, origin_y = get_size_and_coords(f'{path_to_jp2}.aux.xml')

    # Векторы смещения
    offset_vector_x = 10
    offset_vector_y = 10


    # Преобразование контуров в координаты UTM
    utm_contours = transform_contours_to_geo(good_contours, origin_x, origin_y, offset_vector_x, offset_vector_y)

    # Создание трансформера для преобразования из EPSG:32657 в EPSG:4326 (широта, долгота)
    transformer = Transformer.from_crs("EPSG:32657", "EPSG:4326")


    # Преобразование контуров в географические координаты
    geo_contours = transform_utm_to_latlon(utm_contours, transformer)

    # Сохранение контуров в GeoJSON файл
    contours_to_geojson(geo_contours, output_path)

