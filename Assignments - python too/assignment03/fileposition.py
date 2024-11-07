class FilePosition:
    line: int
    column: int

    def __init__(self):
        self.line = 1
        self.column = 1

    def advance(self, i: int):
        self.column += i

    def nextLine(self):
        self.line += 1
        self.column = 1

    def __repr__(self) -> str:
        return "( {}, {} )".format(self.line, self.column)

    def __str__(self) -> str:
        return "line " + str(self.line) + ", column " + str(self.column)

    def __hash__(self) -> int:
        return hash(self.line, self.column)

    def __eq__(self, other) -> bool:
        return self.column == other.column and self.line == other.line

    def __lt__(self, other) -> bool:
        if self.line < other.line:
            return True
        if self.line > other.line:
            return False

        if self.column > other.column:
            return False
        if self.column < other.column:
            return True

        return False


