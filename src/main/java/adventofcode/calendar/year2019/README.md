I suppose I should leave some parting thoughts in case I ever find myself back here in the future. Overall I was feeling alright with what I ended up with. I was was aiming for solutions that were minimal and clear but not at the expense of efficiency, meaning I tried to use faster algorithms where I could (though I'm sure I missed some places), and generally avoided doing things that were obviously slow like making tons of unnecessary copies, though this was an uphill battle with Java some times.

I guess first some notes on general principles and trying to do this in Java then I'll go through my thoughts on specific puzzles.
* I didn't want to use any external libraries except for just the stdlib.
* I tried to avoid getters and setters and making gratuitous abstractions. It's depressing to look back on pages of boilerplate that does nothing when this event is more about finding interesting solutions for me. I was sometimes pushed in this direction anyway in order to share code between parts of a puzzle, but tried not to go overboard with it.
* Trying to do functional style programming in Java felt frustrating at times. The streams API feels very ugly and awkward, so I mostly avoided it. Trying to do basic things with it required an unecessary number of method calls, it's super slow, and you have to provide it with exeternal iterators even though it mostly uses internal iteration. On top of that, although I mostly like how lambdas and method references were added to the language, the inability to update a variable declared in an outer scope was quite annoying and kept coming up and having to be worked around.
* Not being able to use generics with primitives is another unfortunate mistake of history. It's painful to have to duplicate code for int vs long, int[] array vs Object[] array and so on.
* No operator overloading was also annoying and ugly. Would be nice to have this for vectors and BigIntegers.


Well that's that. Now for the puzzles themselves. First some general thoughts.
* I was happy with my Intcode interpreter. The approach of having an unbuffered and buffered version worked out well. I tried to solve these puzzles in different ways. I especially like my solutions for Day 23 (the network simulator) and Day 7 (amplifiers) where I tried out use multithreading.
* That said I feel the two maze Intcode puzzles could have been solved more cleanly (15 & 25).
* Maze handling in general felt clunkly. Maybe I should have created utility classes for character grids, and some general search/graph algos.


And here's some parting thoughts on specific puzzles.

* __Day 3 - Crossed Wires__ The wire segments in the input were fairly long so storing individual points is slow. I tried to do something with logarithmic lookup on segments.
* __Day 6 - Orbit Map__ Using the heights from part 1 to efficiently get the LCA was a neat way to solve this.
* __Day 7 - Amplifiers__ Tried out multi threading for this one. Went ok. I do hate that InterruptedException is a checked exception.
* __Day 8 - Space Image__ Short and sweet.
* __Day 10 - Asteroids__ I liked this puzzle, but this is one where my solution feels over abstracted. I did like the approach though. "Ray casting" to determine visibility/shots felt nicer than grouping by angle and works well since the map is small.
* __Day 12 - N-Body Problem__ Had already solved this one before so new what the trick was. These simulation style puzzles are not so bad in Java.
* __Day 13 - Breakout__ Very cool puzzle idea.
* __Day 14 - Ore Alchemy__ Binary search for the second part felt like a natural way to solve this. Some annoyance with int then long overflowing then begrudgingly having to use BigInteger.
* __Day 15 - Droid__ Didn't like my solution to this and trying to reuse code was annoying.
* __Day 16 - FFT__ I liked this puzzle. Again sharing code was annoying. I wish I was using a language with modules and top-level functions.
* __Day 17 - Scaffold Bot__ Also liked this puzzle. My code feels a bit messy.
* __Day 18 - Mode Maze__ I liked my solution and seems to run fast enough. I know building the subgraph could probably be done more efficiently. For the second part, not sure if solving for each quadrant individually is correct in general, but I've already spent too much time on this.
* __Day 20 - Donut Maze__ Felt very similar to 18. Build a subgraph and Dijkstra.
* __Day 21 - Spring Droid__ This is an actual puzzle. I gave up and looked at my old solutions.
* __Day 22 - Slam Shuffle__ I had also solved this one before.
* __Day 23 - Category Six__ I liked my solution to this one. Felt neat.
* __Day 24 - Bugs__ This one's not that interesting. Just Game of Life.
* __Day 25 - MUD__ Cool puzzle idea for the last day. Code is kind of messy.
