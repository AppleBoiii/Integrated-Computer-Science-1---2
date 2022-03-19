How will you store the precinct information for a 3x3 grid with 3 precincts with 3 cells each?

Store a 2D array with precinct IDs in the cells.

001
012
122

How can you cross over two solutions?

Overlay the two grid and random choose an ID from one of the grids

001       222
012<CROSS>110
122       100

001
012
100

How does your cross over enforce the 3 cell per precinct restriction or what remediation would you do to get back to obeying that restrictions?

It doesn't. 

Count how many ID's of each.  Random pick a cell.  If I have too many of that ID, give it to an ID I don't have enough of.


How would you mutate a solution?

Randomly pick two cells and swap IDs.

001           010
012 -MUTATE-> 012
122           122

How does your mutate enforce the 3 cell per precinct restriction or what remediation would have to be done?

I swap existing values so the number of each is preserved.

How would you measure 	?

I can count the number of shared edges.

0*0 1
*
0 1 2 -count shared edges-> 4
    *
1 2*2

How does fitness work for actual gerrymanded problem? (assumptions: every cell has the same number of peeps)

squirrels(red) vs capybaras(blue)

56 ->44% voted for squirrels and 56% capybaras

Maximize precints that vote for your candidate.
Also, maximize shared edges.


How do you submit solutions?

0123456789
0123456789
0123456789
0123456789
0123456789
0123456789
0123456789
0123456789
0123456789
0123456789
 


