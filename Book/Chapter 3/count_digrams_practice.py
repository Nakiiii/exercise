import os
import load_dictionary
os.chdir("/Book/Chapter 3")

def main():
    """Find digrams and compare occurance"""
    name = input("Please enter a name to analyse the occurences"
                 " of digrams.")
    dis = digrams(name)
    fr = frequency(dis)
    for i in range(len(dis)):
        print("{}: {}".format(dis[i],fr[i]))


def digrams(name):
    """Find every digram in a name."""
    dis = []
    list_name = list(set(list(name)))
    for i in range(len(list_name)):
        for j in range(len(list_name)):
            if i != j:
                dis.append(list_name[i] + list_name[j])
    return dis

def frequency(dis):
    # Todo
    fr = [0] * len(dis)
    amount_dis = 0
    dictionary = load_dictionary.load('2of4brif.txt')
    for word in dictionary:
        for i in range(len(word) - 1):
            if word[i:i+2] in dis:
                amount_dis += 1
                fr[dis.index(word[i:i+2])] += 1
    for i in range(len(fr)):
        fr[i] = fr[i]/amount_dis
    return fr

if __name__ == '__main__':
    main()
