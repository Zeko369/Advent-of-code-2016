steps = input().split(', ')

heading = 0
pos = (0, 0)

visited = set()

x2 = None

for step in steps:
    if step[0] == 'R':
        heading += 90
        heading %= 360
    else:
        heading -= 90
        heading %= 360

    distance = int(step[1:])

    for i in range(distance):
        if heading == 0:
            pos = (pos[0], pos[1] + 1)
        elif heading == 90:
            pos = (pos[0] + 1, pos[1])
        elif heading == 180:
            pos = (pos[0], pos[1] - 1)
        elif heading == 270:
            pos = (pos[0] - 1, pos[1])

        if pos in visited and x2 is None:
            x2 = pos

        visited.add(pos)


print(sum(map(abs, list(pos))))

if x2 is None:
    print('No intersection found')
else:
    print(sum(map(abs, list(x2))))
