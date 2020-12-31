"""Split a route cipher key into two parts."""
import sys

key_dict = {}

while True:
    key = input("Please enter column number.")
    value = input("Please enter reading direction.")
    key_dict[key] = value
    end = input("Enter # to stop, else continue.")
    if end == "#":
        break

print(key_dict)

