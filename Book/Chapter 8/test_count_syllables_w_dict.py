import os
import random
os.chdir("d:\\exercise\\Book\\Chapter 8")
import count_syllables

with open('2of4brif.txt') as in_file:
    words = set(in_file.read().split())

choice = 0
while True:
    choice = input("Enter how many words to check randomly: ")
    if choice.isdigit():
        choice = int(choice)
        break
    else:
        print("Please enter a number!")

random_words = random.sample(words, choice)

for word in random_words:
    try:
        num_syllables = count_syllables.count_syllables(word)
        print(word, num_syllables, end='\n')
    except KeyError:
        print("Could not find syllables for {}.".format(word))
