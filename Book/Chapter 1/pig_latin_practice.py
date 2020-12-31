"""translate a word into pig latin """

CONSONANT = ['a','e','i','o','u']

def main():
    """main"""
    normal_word = input("Which word would you like to translate?\n")
    splitter = normal_word.split(' ')
    while True:
        if len(splitter) > 1:
            normal_word = input("Please enter only one word!\n")
            splitter = normal_word.split(' ')
            continue

        if not normal_word[0] in CONSONANT :
            replacement = normal_word[0]
            normal_word = normal_word[1:]
            normal_word = "{}{}ay".format(normal_word,replacement)
        else:
            normal_word = "{}way".format(normal_word)
        break
    print(normal_word)

if __name__ == "__main__":
    main()
