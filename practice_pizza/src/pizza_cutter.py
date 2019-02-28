import utils
import typing

Point = typing.NamedTuple('Point', [('x', int), ('y', int)]) 
Slice = typing.NamedTuple('Slice', [('start', Point), ('end', Point)])

def get_pizza(lines: list):
    first_line = lines[0].split(' ')
    L = int(first_line[2])
    H = int(first_line[3])
    mapping =  {'T' : 0, 'M' : 1}
    return [[mapping[i] for i in line[:-1]] for line in lines[1:]], L, H

def move_starting_point(pizza_height: int, pizza_width: int, current_x: int, current_y: int):
    if current_x >= pizza_width:
        current_x = 0
        current_y += 1
    else:
        current_x += 1
    return current_x, current_y

def slice_size(s: Slice):
    return ((s.end.x - s.start.x) + 1) * ((s.end.y -  s.start.y) + 1)

def slice_valid(pizza: list, already_cut: list, s: Slice, L, H):
    if slice_size(s) > H:
        return False
    if not cut_possible(already_cut, s):
        return False
    n_tomato, n_mushroom = 0, 0
    for y in range(s.start.y, s.end.y + 1):
        for x in range(s.start.x, s.end.x + 1):
            if pizza[y][x] == 1:
                n_tomato += 1
            else:
                n_mushroom += 1
    return n_tomato >= L and n_mushroom >= L

def cut_possible(already_cut: list, s: Slice):
    for y in range(s.start.y, s.end.y + 1):
        for x in range(s.start.x, s.end.x + 1):
            if already_cut[y][x] == 1:
                return False
    return True

def next_slice(pizza, L, H, start_point: Point, already_cut):
    max_y = len(pizza) -1
    max_x = len(pizza[0]) -1

    def size_y(x):
        return start_point.y + H//(x - start_point.x)

    for x2 in range(min(start_point.x + H, max_x), start_point.x, -1):
        for y2 in range(min(size_y(x2), max_y), start_point.y, -1):
            s = Slice(Point(start_point.x, start_point.y), Point(x2, y2))
            if slice_valid(pizza, already_cut, s, L, H):
                return s
    return None

def cut_out(already_cut, s: Slice):
    for x in range(s.start.x, s.end.x + 1):
        for y in range(s.start.y, s.end.y + 1):
            already_cut[y][x] = 1

def greedy(pizza, L, H):
    current_x, current_y = 0, 0
    pizza_height, pizza_width = len(pizza), len(pizza[0])
    slices = []
    already_cut = [[0 for _ in range(pizza_width)] for _ in range(pizza_height)]
    progress = utils.PizzaSliceProgress(pizza_height)
    while current_y < pizza_height:
        new_slice = next_slice(pizza, L, H, Point(current_x, current_y), already_cut)
        if new_slice:
            current_x = new_slice.end.x
            cut_out(already_cut, new_slice)
            slices.append(new_slice)
        else:
            current_x, current_y = move_starting_point(pizza_height, pizza_width, current_x, current_y)
        if current_x == 0: progress.print_progress(current_y, score(slices))

    return slices

def to_string(slices):
    return [f'{s.start.y} {s.start.x} {s.end.y} {s.end.x}' for s in slices]

def score(slices: list):
    return sum(map(slice_size, slices))

if __name__ == '__main__':
    instances = ['example', 'small', 'medium', 'big']
    for instance in instances:
        print(f'\n\033[95msolving instance {instance}:\033[0m')
        p, L, H = utils.read_input(instance, get_pizza)
        slices = greedy(p, L, H)
        utils.write_output(instance, to_string(slices))