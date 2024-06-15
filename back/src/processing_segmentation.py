import cv2
import os
from PIL import Image
import geojson
import numpy as np

def jp2_to_png(input_jp2_file, output_png_file):
    image = cv2.imread(input_jp2_file, cv2.IMREAD_UNCHANGED)
    if image is None:
        raise RuntimeError(f"Could not read the input JP2 file {input_jp2_file}.")
    success = cv2.imwrite(output_png_file, image)
    if not success:
        raise RuntimeError(f"Could not write the output PNG file {output_png_file}.")

    print(f"Successfully converted {input_jp2_file} to {output_png_file}.")

def split_image(image_path, output_folder, tile_size):
    # Открываем изображение
    image = Image.open(image_path)
    
    # Получаем размеры изображения
    width, height = image.size
    
    # Подсчитываем количество подизображений по горизонтали и вертикали
    cols = width // tile_size
    rows = height // tile_size
    
    # Создаем выходную папку, если она не существует
    if not os.path.exists(output_folder):
        os.makedirs(output_folder)
    
    # Разбиваем изображение на подизображения
    for row in range(rows):
        for col in range(cols):
            left = col * tile_size
            upper = row * tile_size
            right = left + tile_size
            lower = upper + tile_size
            
            # Создаем подизображение
            tile = image.crop((left, upper, right, lower))
            
            # Сохраняем подизображение
            tile.save(os.path.join(output_folder, f'tile_{row}_{col}.png'))

    return cols, rows




# Функция для преобразования контуров в географические координаты
def transform_contours_to_geo(contours, origin_x, origin_y, offset_x, offset_y):
    transformed_contours = []
    for contour in contours:
        transformed_contour = []
        for point in contour:
            x_img, y_img = point[0]
            x_geo = origin_x + x_img * offset_x
            y_geo = origin_y - y_img * offset_y
            transformed_contour.append([x_geo, y_geo])
        transformed_contours.append(np.array(transformed_contour))
    return transformed_contours



# Функция для преобразования координат UTM в географические координаты
def transform_utm_to_latlon(contours, transformer):
    latlon_contours = []
    for contour in contours:
        latlon_contour = []
        for x_utm, y_utm in contour:
            lat, lon = transformer.transform(x_utm, y_utm)
            latlon_contour.append([lat, lon])
        latlon_contours.append(np.array(latlon_contour))
    return latlon_contours

# Функция для преобразования контуров в формат GeoJSON
def contours_to_geojson(contours, filename):
    features = []
    for contour in contours:
        # Преобразование каждого контура в GeoJSON формат
        coordinates = [[lon, lat] for lat, lon in contour]
        # Создание GeoJSON объекта
        polygon = geojson.Polygon([coordinates])
        features.append(geojson.Feature(geometry=polygon))

    # Создание коллекции GeoJSON объектов
    feature_collection = geojson.FeatureCollection(features)

    # Сохранение в файл
    with open(filename, 'w') as f:
        geojson.dump(feature_collection, f)

def get_size_and_coords(file_name):
    f = open(file_name, 'r')
    r = f.read()
    f.close()


    start_pos = r.index('<gml:pos>')
    end_pos = r.index('</gml:pos>')

    pos = r[start_pos+9: end_pos].split()

    return int(pos[0]), int(pos[1])

