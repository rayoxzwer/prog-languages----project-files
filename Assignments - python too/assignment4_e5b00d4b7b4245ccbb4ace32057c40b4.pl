cartesian(X, Z) :-
cartesian(X, Z, []).
cartesian([], _, []).
cartesian([TobeX | TubiX], Y, Z):-
    makepairs(TobeX, Y, TobeZ),
    cartesian(TubiX, Y, TubiZ),
    append(TobeZ, TubiZ, Z).

makepairs(TobeX, [], []).
makepairs(TobeX, [TobeY | TubiY], [TobeZ | TubiZ]):-
    TobeZ = pair(TobeX, TobeY),
    makepairs(TobeX, TubiY, TubiZ).

deepsum([], 0).
deepsum([X | Y], Res):-
    atom(X) ->
    deepsum(Y, Sum),
    Res is Sum;

    number(X),
    deepsum(Y, Sum),
    Res is Sum + X;

    string(X) ->
    deepsum(Y, Sum),
    Res is Sum.

deepsum([X|Y], Res) :-
    deepsum(X, R1),
    deepsum(Y, R2),
    Res is R1 + R2.

deepsum([], 0, 0).
deepsum([X | Y], Pos, Neg) :-
    atom(X) ->
    deepsum(Y, Positive, Negative),
    Pos is Positive,
    Neg is Negative;

    number(X), X > 0 ->
    deepsum(Y, Positive, Negative),
    Pos is Positive + X,
    Neg is Negative;

    number(X), X < 1 ->
    deepsum(Y, Positive, Negative),
    Pos is Positive,
    Neg is Negative + X;

    string(X) ->
    deepsum(Y, Positive, Negative),
    Pos is Positive,
    Neg is Negative.

deepsum([X | Y], Pos, Neg) :-
    deepsum(X, Positive1, Negative1),
    deepsum(Y, Positive2, Negative2),
    Pos is Positive1 + Positive2,
    Neg is Negative1 + Negative2.


numbers([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]).

select(X, [TopL1 | BotL1], BotL2):-
    X = TopL1 ->
    BotL2 = BotL1.

select(X, [TopL1, SecTopL1 | BotL1], [TopL2 | BotL2]):-
    TopL2 = TopL1,
    L1 = [SecTopL1 | BotL1],
    select(X, L1, BotL2).


sumfits(N1, N2, N3, N4):-
    N4 is 34 - (N1 + N2 + N3).


start(S, Free16):-
    S = [ [A11, A12, A13, A14],
          [A21, A22, A23, A24],
          [A31, A32, A33, A34],
          [A41, A42, A43, A44] ],
    numbers(Free16).


printsquare([]).
printsquare([R1, R2, R3, R4]):-
    write(R1),
    nl, write(R2),
    nl, write(R3),
    nl, write(R4), nl.

step1(S, Free7):-
    S = [ [A11, A12, A13, A14],
          [A21, A22, A23, A24],
          [A31, A32, A33, A34],
          [A41, A42, A43, A44] ],
    start(S, Free16),
    select(A11, Free16, Free15),
    select(A12, Free15, Free14),
    select(A13, Free14, Free13),
    sumfits(A11, A12, A13, A14),
    select(A14, Free13, Free12),
    A11 < A14,

    select(A21, Free12, Free11),
    select(A31, Free11, Free10),
    sumfits(A11, A21, A31, A41),
    select(A41, Free10, Free9),
    A11 < A41, A41 < A14,

    select(A23, Free9, Free8),
    sumfits(A41, A14, A23, A32),
    select(A32, Free8, Free7).

step2(S, Free4):-
    S = [ [A11, A12, A13, A14],
          [A21, A22, A23, A24],
          [A31, A32, A33, A34],
          [A41, A42, A43, A44] ],
    step1(S, Free7),
    select(A22, Free7, Free6),
    sumfits(A21, A22, A23, A24),
    select(A24, Free6, Free5),

    sumfits(A12, A22, A32, A42),
    select(A42, Free5, Free4).

step3(S, Free2):-
    S = [ [A11, A12, A13, A14],
          [A21, A22, A23, A24],
          [A31, A32, A33, A34],
          [A41, A42, A43, A44] ],
    step2(S, Free4),
    select(A33, Free4, Free3),
    sumfits(A11, A22, A33, A44),
    select(A44, Free3, Free2),
    A11 < A44.

step4(S, Free0):-
    S = [ [A11, A12, A13, A14],
          [A21, A22, A23, A24],
          [A31, A32, A33, A34],
          [A41, A42, A43, A44] ],
    step3(S, Free2),
    sumfits(A41, A42, A44, A43),
    select(A43, Free2, Free1),
    sumfits(A14, A24, A44, A34),
    select(A34, Free1, Free0).

printall:-
    step4(S, Free0),
    Free0 = [],
    printsquare(S), fail.

