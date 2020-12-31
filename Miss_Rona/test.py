import requests
import json
import sys
from datetime import datetime, timedelta
import tkinter as tk
from tkinter import *
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg


def get_population(country):
    url = "https://countriesnow.space/api/v0.1/countries/population"
    try:
        response = requests.get(url)
        response.raise_for_status()
    except requests.exceptions.RequestException as e:
        print("There was a problem with the connection.\n"
              "{}"
              "Exiting program".format(e), file=sys.stderr)
        sys.exit(1)
    population = json.loads(response.text)
    for i in range(len(population["data"])):
        if population["data"][i]["country"].lower() == country:
            return population["data"][i]["populationCounts"][-1:][0]["value"]
    print("country population not found", file=sys.stderr)
    sys.exit(1)


print(get_population('united states'))