Started to feel tired/burned out towards the end of this one, and just wanted to be done with it.
For the sake of closure here are some thoughts.

* __Day 1: Chronal Calibration__ Tried to optimise the second part. Use the fact that the frequencies all increase by a fixed step every loop (which was the answer to part 1), then find the frequency pairs that are equal modulo the step and pick the pair with the fewest steps apart. More optimising by using an array instead of hash map to do the grouping.
* __Day 2: Inventory Management System__ Use a hash map for part 2. Similar to day 1.
* __Day 3: No Matter How You Slice It__ Did this the straightforward way by dumping everything into a big array.
* __Day 4: Repose Record__ Cool puzzle idea. Just some log parsing.
* __Day 5: Alchemical Reduction__ Use a stack for unreacted elements. Just need to check if the last element reacts and if so pop it and proceed.
* __Day 6: Chronal Coordinates__ Neat puzzle. Was easier doing a sort of scan line thing rather than trying to flood fill.
* __Day 7: The Sum of Its Parts__ Usual method to topological sort didn't work because of the requirement for lexicographical ordering. Tried to clean things up a bit.
* __Day 8: Memory Maneuver__ This one was pretty easy. You don't need to store the tree and could do the computation on the fly, but it's not enough of a difference to bother with.
* __Day 9: Marble Mania__ Not sure how to optimise this beyond using a linked list.
* __Day 10: The Stars Align__ Solved this analytically, since it's a linear function. Spent too much time on this, trying to find a nice expression in terms of the variance. I end up recomputing some values which is wasteful, but I don't care. Part 2 asks for the time for the stars to align, not sure how you could solve part 1 and not already have that.
* __Day 11: Chronal Charge__ Used a prefix sum table. Still a bit slow.
* __Day 12: Subterranean Sustainability__ First part was sort of standard Game of Life. Trick to the second part was to realise the pattern eventually stabilises and just shifts forward.
* __Day 13: Mine Cart Madness__ Not much to say on this one.
* __Day 14: Chocolate Charts__ Slow but couldn't see any way to speed it up significantly.
* __Day 15: Beverage Bandits__ Slow and a little messy/clunky. This puzzle took way too long. Probably some optimisation that could be done with remembering the previous path in case it hasn't changed and binary search on the second part.
* __Day 16: Chronal Classification__ Not sure what to say on this one. Cool puzzle idea.
* __Day 17: Reservoir Research__ Another cool and original puzzle idea. Spent a bit too much time on off-by-one errors.
* __Day 18: Settlers of The North Pole__ Just another Game of Life. This time it's periodic.
* __Day 19: Go With The Flow__ I've done these puzzles before so knew to decompile it.
* __Day 20: A Regular Map__ Ended up with a general solution though it generates the maze as an intermediate step. First steps through the regex keeping track of all current endpoints. It's important to deduplicate the endpoints since a lot of branches all lead back to the same point ("detours").
* __Day 21: Chronal Conversion__ While decompiling the code realised there's just a single check with A, that causes the program to halt. Both parts of the puzzle required to check for this. Second part takes a while to run.
* __Day 22: Mode Maze__ Interesting setup for the puzzle. Second part is slow. Don't know how to make significant improvements.
* __Day 23: Experimental Emergency Teleportation__ Thankfully I had done this one before so copied my old solution. Pretty sure I got the answer from reddit originally.
* __Day 24: Immune System Simulator 20XX__ Blergh, another simulator puzzle.
* __Day 25: Four-Dimensional Adventure__ Also just copied my old solution for this one.
