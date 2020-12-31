import load_dictionary
import os

os.chdir('/Book/Chapter 3')

word_list = load_dictionary.load('2of4brif.txt')
anagram_list = []
name = input('Please enter a name:\n').lower()

name_sorted = sorted(name)
for word in word_list:
    if sorted(word) == name_sorted and word != name:
        anagram_list.append(word)

print()
if len(anagram_list) == 0:
    print('You need a larger dictionary or a new name!')
else:
    print("Anagrams =", *anagram_list,sep='\n')