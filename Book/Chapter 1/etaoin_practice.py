"""Map letters into dictionary."""

import pprint
import re
import collections

def main():
    """Main function"""
    chart = collections.defaultdict(list)
    sentence = input("Enter a sentence to determine the amount"
                     " of each character:\n").lower()
    pattern = re.compile("[a-z]")
    for i in sentence:
        if re.match(pattern,i):
            chart[i].append(i)
    pprint.pprint(chart)

if __name__ == "__main__":
    main()
