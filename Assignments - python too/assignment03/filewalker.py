from fileposition import FilePosition
from typing import Tuple, Generator, TextIO

import os
import syntax
import copy
import codecs


class FileWalker:
    topdir: str  # Python uses strings for representing file names.

    def __init__(self, topdir):
        self.topdir = topdir  # No checks here.

    def recDirIterator(self) -> Generator[Tuple[str, str, FilePosition], None, None]:
        if os.path.isdir(self.topdir) and os.path.exists(self.topdir):
            for tree in list(os.walk(self.topdir)):
                for files in tree[2]:
                    file_name = os.path.join(tree[0], files)
                    file = open(file_name, "r", encoding="utf8")
                    for t in self.fileIterator(file):
                        out_put = t[0]
                        clone_pos = t[1]
                        yield file_name, out_put, clone_pos
        else:
            raise OSError

    @staticmethod
    def fileIterator(f: TextIO) -> Generator[Tuple[str, FilePosition], None, None]:
        fpos = FilePosition()
        fpos.column = 1
        fpos.line = 1

        ch = f.read()
        out_put = ""
        for each_ch in ch:
            if syntax.inWord(each_ch) == False:
                sz = len(out_put)
                if sz > 0:
                    clone = copy.copy(fpos)
                    fpos.column = fpos.column + sz
                    yield out_put, clone
                    out_put = ""
                fpos.column = fpos.column + 1

            if syntax.inWord(each_ch):
                out_put = out_put + each_ch

            if syntax.isNewLine(each_ch):
                fpos.column = 1
                fpos.line = fpos.line + 1
                out_put = ""
        sezi = len(out_put)
        if sezi > 0:
            clone = copy.copy(fpos)
            fpos.column = fpos.column + len(out_put)
            yield out_put, clone
        f.close()

    def __repr__(self) -> str:
        return "FileWalker: " + self.topdir

    def __str__(self) -> str:
        return "FileWalker: " + self.topdir
