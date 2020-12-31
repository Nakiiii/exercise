plaintext = """Let us cross over the river and rest under the shade of the trees"""

def main():
    """Run program to encrypt message using 2-rail fence cipher."""
    message = prep_plaintext()
    rails = build_rails(message)
    encrypt(rails)

def prep_plaintext():
    """"""