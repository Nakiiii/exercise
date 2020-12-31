import load_dictionary
import os
os.chdir('/Book/Chapter 2')
word_list = load_dictionary.load('2of4brif.txt')
cleanups = []

def cleanup():
    words = set(word_list)
    for word in words:
        if len(word) > 1:
            cleanups.append(word)
    return cleanups

print("{}".format(cleanup()))