# Load a list of first names
# Load a list of second names
# choose a first name at random
# Assign the name to a variable
# Choose a second name at random
# Assign the name to a variable
# Print the names on the screen in order and in red font
# Ask the user to quit or play again
# If user plays again:
#   repeat
# If user quits:
#   end and exit
"""Picks a random name"""
import sys
import random

print("Welcome to the Psych 'Sidekick Name Picker'.")
print("A name just like Sean would pick for Gus:\n\n")

first = ('Baby Oil','Wheezy','Zicky Vicky','Peepee Poopoo',
         'Spoderpeg', 'Teetee', 'Taataa')
last = ('Appleyard', 'Orange County', 'Lemon City', 'sitxyNine',
        'Bigmeat', 'Tinyshlong', 'Henderson')

while True:
    firstName = random.choice(first)
    lastName = random.choice(last)

    print('{} {}'.format(firstName,lastName),file=sys.stderr)

    again = input('\nDo you want to continue? (Press ENTER else n to quit)\n')
    if again.lower() == 'n':
        break

input('\nPress ENTER to exit')
