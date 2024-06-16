from geoalchemy2.elements import WKBElement
from shapely import wkb
from shapely.geometry import mapping


def null_to_empty(d: dict):
    for i in d.keys():
        if d[i] is None:
            d[i] = 'null'
    return d


def wkb_element_to_wkt(element: WKBElement):
    if element is not None:
        shape = wkb.loads(bytes(element.data))
        return mapping(shape)
    return {'type': 'null', 'coordinates': 'null'}
