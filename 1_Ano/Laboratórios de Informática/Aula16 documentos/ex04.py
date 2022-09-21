import json


def main():
    data = [{"name": "John",
             "age": 30,
             "cars": [
                 {"name": "Ford", "models": ["Fiesta", "Focus", "Mustang"]},
                 {"name": "BMW", "models": ["320", "X3", "X5"]},
                 {"name": "Fiat", "models": ["500", "Panda"]}]
             },
            [
        {'time': 1394984189, 'name': 'cpu', 'value': 12},
                {'time': 1394984190, 'name': 'cpu', 'value': 19}
    ]
    ]
    print(json.dumps(data, indent=4))


main()
