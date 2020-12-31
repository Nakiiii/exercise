import requests
import json
from datetime import datetime, timedelta
import tkinter as tk
from tkinter import *
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg

class App(tk.TK()):

    cases = []
    dates = []

    def __init__(self):
        """Initialize root"""


    def get_covid_numbers(country):
        """Get covid numbers in country from last week

        returns: DataFrame {Numbers[],
                            Dates[]}
        """
        # using postman API
        url = "https://api.quarantine.country/api/v1/spots/week?region={}".format(country)
        try:
            response = requests.get(url)
            response.raise_for_status()
        except requests.exceptions.RequestException as e:
            print("There was a problem with the connection.\n"
                  "{}"
                  "Exiting program".format(e), file=sys.stderr)
            sys.exit(1)

        return numbers, dates

