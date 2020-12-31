import requests
import json
import sys
import tkinter as tk
from tkinter import *
from tkinter import ttk
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg

"""GUI definitions"""
root = tk.Tk()
root.configure(background='white')
Grid.rowconfigure(root, 0, weight=1)
Grid.columnconfigure(root, 0, weight=1)
frame = LabelFrame(root, borderwidth=0, highlightthickness=0, bg='white', text="Country")
frame_values_c = LabelFrame(root, borderwidth=0, highlightthickness=0, bg='white')
frame_recovered = LabelFrame(root, borderwidth=0, highlightthickness=0, bg='white')
frame.grid(row=0, column=0, sticky='N')
frame_values_c.grid(row=1, column=1)
frame_recovered.grid(row=2, column=1)
cases = []
dates = []
recovered = []
total_cases = 0
total_recovered = 0
total_deaths = 0
countries = []


def get_all_countries():
    """Get all countries in Postman API and population API to find
        which countries are not included, or have different names.
    """
    url = "https://api.covid19api.com/summary"
    global countries
    try:
        response = requests.get(url)
        response.raise_for_status()
    except:
        print("Couldn't find countries in Poastman")
        sys.exit(1)
    post_countries = json.loads(response.text)["Countries"]
    for i in range(len(post_countries)):
      countries.append(post_countries[i]["Country"].lower())

    # countries of population API
    url = "https://countriesnow.space/api/v0.1/countries/population"
    try:
        response = requests.get(url)
        response.raise_for_status()
    except:
        print("Couldn't find countries population")
        sys.exit(1)
    pop_countries = json.loads(response.text)
    p_c = []
    for i in range(len(pop_countries["data"])):
        p_c.append(pop_countries["data"][i]["country"].lower())
    countries = list(set(countries) & set(p_c))
    countries.sort()


def get_covid_numbers(country):
    """Get covid numbers in country from last week
    return: cases, dates | both in list format
    """
    global cases, dates, recovered, total_cases, total_recovered, total_deaths
    # using postman API
    url = "https://api.covid19api.com/dayone/country/{}".format(country)
    try:
        response = requests.get(url)
        response.raise_for_status()
    except:
        print("There was a problem with the connection.\n"
              "Please look up how to spell the country on https://api.covid19api.com/countries", file=sys.stderr)
        sys.exit(1)

    last_week = json.loads(response.text)
    for i in last_week:
        cases.append(i["Confirmed"])
        dates.append(i["Date"][:10])
        recovered.append(i["Recovered"])
    total_cases = cases[-1]
    total_recovered = recovered[-1]
    total_deaths = last_week[-1]["Deaths"]
    temp_cases = []
    temp_recovered = []
    temp_cases.append(cases[0])
    temp_recovered.append(recovered[0])
    for i in range(1, len(cases)):
        temp_cases.append(cases[i] - cases[i - 1])
        temp_recovered.append(recovered[i] - recovered[i - 1])
    cases = temp_cases
    recovered = temp_recovered
    return cases, dates, recovered


def chart_to_gui(lw_cases, lw_dates, lw_recovered):
    """Transfer values to Canvas, add textfield and button"""
    plt.rc('xtick', labelsize=8)
    fig_cases = plt.Figure(figsize=(8, 4))
    fig_cases.add_subplot(111).bar(lw_dates, lw_cases)
    fig_cases.suptitle('CASES LAST WEEK', fontsize=20)
    fig_recovered = plt.Figure(figsize=(8, 4))
    fig_recovered.add_subplot(111).bar(lw_dates, lw_recovered)
    fig_recovered.suptitle('RECOVERED LAST WEEK', fontsize=20)
    canvas_cases = FigureCanvasTkAgg(fig_cases, root)
    canvas_cases.get_tk_widget().grid(row=1, sticky='N' + 'S' + 'E' + 'W')
    canvas_recovered = FigureCanvasTkAgg(fig_recovered, root)
    canvas_recovered.get_tk_widget().grid(row=2, sticky='N' + 'S' + 'E' + 'W')


def click(event):
    country = combo_Box.get()
    cases, dates, recovered = get_covid_numbers(country)
    lw_cases = cases[-7:]
    lw_dates = dates[-7:]
    lw_recovered = recovered[-7:]
    chart_to_gui(lw_cases, lw_dates, lw_recovered)
    values(country)


def r_value(cases):
    over = sum(cases[-4:])
    under = sum(cases[-8:-4])
    r_value = over / under
    return r_value


def seven_days(country):
    population = get_population(country)
    cases
    seven_day_cases = cases[-2] - cases[-8]
    return seven_day_cases / population * 100000


def get_population(country):
    url = "https://countriesnow.space/api/v0.1/countries/population"
    try:
        response = requests.get(url)
        response.raise_for_status()
    except:
        print("There was a problem with the connection.\n"
              "{}"
              "Exiting program", file=sys.stderr)
        sys.exit(1)
    population = json.loads(response.text)
    for i in range(len(population["data"])):
        if population["data"][i]["country"].lower() == country:
            return population["data"][i]["populationCounts"][-1:][0]["value"]
    print("country population not found", file=sys.stderr)
    sys.exit(1)


def values(country):
    r = r_value(cases)
    seven = seven_days(country)
    l_r = Label(frame_values_c, text="Reproduction Value = {}".format(r))
    l_s = Label(frame_values_c, text="Seven days incidence = {}".format(seven))
    l_tc = Label(frame_values_c, text="Total cases = {}".format(total_cases))
    l_rec = Label(frame_recovered, text="Total recovered = {}".format(total_recovered))
    l_death = Label(frame_recovered, text="Total deaths = {}".format(total_deaths))
    l_s.grid(row=0, column=0)
    l_r.grid(row=1, column=0)
    l_tc.grid(row=2, column=0)
    l_rec.grid(row=0, column=0)
    l_death.grid(row=2, column=0)


get_all_countries()
combo_Box = ttk.Combobox(frame, value=countries)
combo_Box.current(56)
combo_Box.bind("<<ComboboxSelected>>", click)
combo_Box.grid(row=0, column=0)
root.title('Miss Rona App')
countries = []
root.mainloop()
