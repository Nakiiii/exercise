import os
import random
import math
import pygame as pg
os.chdir("d:\\exercise\\exercise\\Book\\Chapter 14")

WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
LT_BLUE = (173, 216, 230)

class Satelitte(pg.sprite.Sprite):
    """Satelitte object that rotates to face planet & crashes & burns."""

    def __init__(self, background):
        super().__init__()
        self.background = background
        self.image_sat = pg.image.load("satellite.png").convert()
        self.image_crash = pg.image.load("satellite_crash_40x33.png").convert()
        self.image = self.image_sat
        self.rect = self.image.get_rect()
        self.image.set_colorkey(BLACK)

        self.x = random.randrange(315, 425)
        self.y = random.randrange(70, 180)
        self.dx = random.choice([-3, 3])
        self.dy = 0
        self.heading = 0
        self.fuel = 100
        self.mass = 1
        self.distance = 0
        self.thrust = pg.mixer.Sound("thrust_audio.ogg")
        self.thrust.set_volume(0.07)

    def thruster(self, dx, dy):
        """Execute actions associated with firing thrusters"""
