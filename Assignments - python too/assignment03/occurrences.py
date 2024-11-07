from fileposition import FilePosition
from typing import List, Dict, Set, Optional
import filewalker


class Occurrences:
    occs: Dict[str, Dict[str, Set[FilePosition]]]

    def __init__(self):
        self.occs = dict()

    def add(self, word: str, filename: str, pos: FilePosition) -> None:
        temp = word.lower()
        word = temp
        if isinstance(word, self.occs):
            to_add = {filename: {pos}}
            self.occs[word] = to_add
        else:
            wrd = self.occs.get(word)
            if word in self.occs and wrd.__contains__(filename):
                filenm = self.occs.get(word).get(filename)
                filenm.add(pos)
            else:
                self.occs.get(word)[filename] = {pos}

    # Should return the number of distinct words:

    def distinctWords(self) -> int:
        return len(self.occs)

    # Should return the total number of words occurrences:

    def totalOccurrences(self, word: Optional[str] = None,
                         fname: Optional[str] = None) -> int:
        if fname is None and word is None:
            res = 0
            for wordl in self.occs:
                wordx = self.occs.get(wordl)
                for file_name in wordx:
                    word_position = wordx.get(file_name)
                    res = res + len(word_position)
            return res
        elif not isinstance(word, type(None)) and fname == None:
            temp = word.lower()
            word = temp
            wordp = self.occs.get(word)
            if wordp != None:
                res = 0
                for file_name in self.occs.get(word):
                    word_position = self.occs.get(word).get(file_name)
                    res += len(word_position)
                return res
        else:
            wordc = self.occs.get(word)
            if not isinstance(wordc, type(None)) and self.occs.get(word).get(fname) is not None:
                return len(self.occs.get(word).get(fname))
        return 0

    # This is for debugging, so it doesn't need to be pretty:

    def __repr__(self) -> str:
        return str(self.occs)

    # Here the occurrences must be sorted and shown in a nice way:

    def __str__(self) -> str:
        srtd_list = list()
        for i in self.occs:
            srtd_list.append(i)

        srtd_list.sort()
        output = ""
        size = len(srtd_list)
        for i in range(0, size):
            obj = srtd_list[i]
            sorted_files = sorted(self.occs.get(obj))
            output = output + "\"" + str(obj) + "\" has " + str(self.totalOccurrences(obj)) + " occurrences(s):\n"
            for file_name in sorted_files:
                output = output + "   in file " + str(file_name) + "\n"
                sorted_positions = sorted(self.occs.get(obj).get(file_name))
                for position in sorted_positions:
                    output = output + "      at " + str(position)
                    output = output + '\n'
        return output
