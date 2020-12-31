"""Compare most used letters in English alphabet (ETAOIN) and decide
if substitution- or transposition cipher"""
import sys
import os
from collections import Counter

os.chdir("/Book/Chapter 4")

CUT_OFF = 0.5

def load(filename):
    with open(filename) as f:
        return f.read().strip()

try:
    c_text = load('cipher_a.txt')
except IOError as e:
    print("{}. Terminating program.".format(e),
          file=sys.stderr),
    sys.exit(1)

top_6 = Counter(c_text.lower()).most_common(6)
print("\nMost common letters in cyphertext are {}".format(top_6))
print("\nMost common letters in the English language are ETAOIN.")

c_top_6 = {i[0] for i in top_6}

TARGET = 'etaoin'
count = 0

for letter in TARGET:
    if letter in c_top_6:
        count += 1

if count/len(c_text) >= 0.5:
    print("\nProbably substitution cipher.")
else:
    print("\nProbably transposition cipher.")