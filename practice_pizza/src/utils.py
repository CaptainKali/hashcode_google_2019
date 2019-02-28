import sys

def read_input(instance, parse_function):
    f = open('../input/' + instance + '.in', 'r')
    content = f.readlines()
    f.close()
    return parse_function(content)

def write_output(instance, lines):
    f = open ('../output/' + instance + '.out', 'w')
    f.write(str(len(lines))+'\n')
    for line in lines :
        f.write(line+'\n')
    f.close()

class PizzaSliceProgress():
    def __init__ (self, total):
        self.total = total
        self.last_score = None
        self.bar_length = 50
        self.OKGREEN = '\033[92m'
        self.ENDC = '\033[0m'

    def print_progress(self, count, current_score):
        if count % (self.total // min(self.total, 10)) == 0:
            filled_len = self.get_length(count)
            percents = self.get_percents(count)
            bar = self.get_bar(filled_len)

            score_string = self.get_score(current_score)
            sys.stdout.write(f'\r{bar} {format(percents, "2.0f")} %\t {score_string}')
            sys.stdout.flush()

            self.last_score = current_score
        else:
            print('\n')

    def get_score(self, current_score):
        new_score = self.last_score and self.last_score < current_score
        score_string = f'[current score {self.OKGREEN if new_score else self.ENDC}{current_score}{self.ENDC}]'
        return score_string

    def get_bar(self, filled_len):
        return '[' + self.OKGREEN + '=' * filled_len + self.ENDC + '-' * (self.bar_length - filled_len) + ']'

    def get_percents(self, count):
        return round(float(count) / self.total * 100, 1)

    def get_length(self, count):
        return int(round(self.bar_length * count / float(self.total)))