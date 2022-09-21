import requests,json,sys

def main():
    address = "vila cova da lixa"
    serviceurl = "https://nominatim.openstreetmap.org/search.php?format=json&q=%s" % address
    r = requests.get(serviceurl)
    print(json.dumps(r.json(),indent=2))
if __name__ == "__main__":
    main()