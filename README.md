# Puppy-8
 +1 CHIP-8 Implementation (ho lord)
 
> Chip-8 is a simple, interpreted, programming language which was first used on some do-it-yourself computer systems in the late 1970s and early 1980s. The COSMAC VIP, DREAM 6800, and ETI 660 computers are a few examples. These computers typically were designed to use a television as a display, had between 1 and 4K of RAM, and used a 16-key hexadecimal keypad for input. The interpreter took up only 512 bytes of memory, and programs, which were entered into the computer in hexadecimal, were even smaller. - [Cowgod's Chip-8 Technical Reference](http://devernay.free.fr/hacks/chip8/C8TECH10.HTM)


## Getting started
First, install java </br>
https://java.com/en/download/help/download_options.xml

After that, build with Maven:
```
mvn install
```
## Usage
Lastly, execute in command line:
```
java -jar <puppy8-name>.jar <rom-name>.ch8
```

Controls:
```
Chip8 Key   Keyboard
---------   ---------
 1 2 3 C     1 2 3 4
 4 5 6 D     q w e r
 7 8 9 E     a s d f
 A 0 B F     z x c v
```
### App roms (.ch8)
https://github.com/dmatlack/chip8/tree/master/roms </br>
https://www.zophar.net/pdroms/chip8/chip-8-games-pack.html

### Extras
https://en.wikipedia.org/wiki/CHIP-8 </br>
https://github.com/tobiasvl/awesome-chip-8 </br>
https://github.com/rafzby/chip8-emulator/ 
