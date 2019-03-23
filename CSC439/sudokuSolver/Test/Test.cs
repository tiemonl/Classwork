//using NUnit.Framework;
using System;
using sudokuSolver;
using NUnit.Framework;

namespace Test
{

    
    public class SudokuTest
    {


        
		[Test]
        public void canSolveSudokuBoard()
        {
            string notSolved = "470200809050069003009000201000008000010604002600700510025106007100900020000320080";
            string solved = "473251869251869473869473251732518694518694732694732518325186947186947325947325186";

            string notSolved2 = "607040509008530607030027008001400306080090201096201405714800960050902004060700850";
            string solved2 = "627148539148539627539627148271485396485396271396271485714853962853962714962714853";
            //string test = "027850000905030087083710250000100025206345800051090040032500400500473002004021508";



            Sudoku sudoku = new Sudoku();
            string testSolve = sudoku.solver(notSolved);
            string testSolve2 = sudoku.solver(notSolved2);

            Assert.AreEqual(solved, testSolve);
            Assert.AreEqual(solved2, testSolve2);
        }
		
		[Test]
		public void canGenerateBoard() {
			int[,] grid2 = new int[9, 9];
			string s = "";
			Sudoku sudoku = new Sudoku();
			sudoku.Draw(ref grid2, out s);
			Assert.AreEqual(81, s.Length);
		}

		[Test]
		public void canRandomize() {
			string solved = "473251869251869473869473251732518694518694732694732518325186947186947325947325186";
			string original = "473251869251869473869473251732518694518694732694732518325186947186947325947325186";
			Sudoku sudoku = new Sudoku();
			sudoku.Randomize(out solved, out solved);
			Assert.AreNotEqual(solved, original);
		}
    }
}