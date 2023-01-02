import sys

lines = [line.strip() for line in sys.stdin.readlines()]

keypad_part_1 = [
    [None, None, None, None, None],
    [None, 1,    2,    3,    None],
    [None, 4,    5,    6,    None],
    [None, 7,    8,    9,    None],
    [None, None, None, None, None]
]

keypad_part_2 = [
    [None, None, None, None, None, None, None],
    [None, None, None, 1,    None, None, None],
    [None, None, 2,    3,    4,    None, None],
    [None, 5,    6,    7,    8,    9,    None],
    [None, None, 'A',  'B',  'C',  None, None],
    [None, None, None, 'D',  None, None, None],
    [None, None, None, None, None, None, None],
]


def get_move(c: str, pos: tuple[int, int], keypad):
    match c:
        case 'U':
            if keypad[pos[0] - 1][pos[1]] is not None:
                return pos[0] - 1, pos[1]
        case 'D':
            if keypad[pos[0] + 1][pos[1]] is not None:
                return pos[0] + 1, pos[1]
        case 'L':
            if keypad[pos[0]][pos[1] - 1] is not None:
                return pos[0], pos[1] - 1
        case 'R':
            if keypad[pos[0]][pos[1] + 1] is not None:
                return pos[0], pos[1] + 1

    return pos


x1 = []
x2 = []

pos_1 = (len(keypad_part_1[0]) // 2, len(keypad_part_1) // 2)
pos_2 = (3, 1)

for i, line in enumerate(lines):
    for c in line:
        pos_1 = get_move(c, pos_1, keypad_part_1)
        pos_2 = get_move(c, pos_2, keypad_part_2)

    x1.append(keypad_part_1[pos_1[0]][pos_1[1]])
    x2.append(keypad_part_2[pos_2[0]][pos_2[1]])

print(''.join(str(x) for x in x1))
print(''.join(str(x) for x in x2))
