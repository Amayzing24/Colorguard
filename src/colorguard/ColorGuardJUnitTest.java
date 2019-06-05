
package colorguard;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class ColorGuardJUnitTest {
	
		@Test
		public void LocationtestColandRow()
		{
			Location loc = new Location(13, 2);
			loc.setCol(3);
			loc.setRow(14);
			assertTrue(loc.getRow() == 14 );
			assertTrue( loc.getCol() == 3);
			assertEquals( loc.toString(),  "(14, 3)");
		}
		@Test
		public void BasicPiece() {
			
			GameBoard gb = new GameBoard();
			BasicPiece bp = new BasicPiece(new Location( 5, 7), "Red");
			assertEquals(bp.getLocation().getRow(), 5);
			assertEquals(bp.getLocation().getCol(), 7);
			
			BasicPiece bptwo = new BasicPiece(new Location( 5, 9), "Blue");
			assertEquals( gb.isStartingZone("Blue", bptwo.getLocation()), false);
			
			BasicPiece bpthree = new BasicPiece(new Location( 13, 0), "Blue");
			assertEquals(bpthree.getLocation().getRow(), 13);
			assertEquals(bpthree.getLocation().getCol(), 0);
			
			BasicPiece bpfour = new BasicPiece( new Location(11, 5), "Blue");
			assertEquals( gb.isStartingZone("Blue", bpfour.getLocation()), false);
					
		}
		
		@Test
		public void BasicPieceGetMovement() {
			
			GameBoard gbFour = new GameBoard();
			BasicPiece bpFour = new BasicPiece( new Location(4,4), "Red");
			BasicPiece bpOppFour = new BasicPiece( new Location(5,4), "Blue");
			gbFour.addPiece(bpFour);
			gbFour.addPiece(bpOppFour);
			Location newMoveFour = new Location(5, 4);
			HashSet<Location> movesFour = bpFour.getPossibleMovements(gbFour);
			assertTrue(movesFour.contains(newMoveFour));
			
			GameBoard gbFive = new GameBoard();
			BasicPiece bpFive = new BasicPiece( new Location(4,4), "Red");
			BasicPiece bpOppFive = new BasicPiece( new Location(4,3), "Blue");
			gbFive.addPiece(bpFive);
			gbFive.addPiece(bpOppFive);
			Location newMoveFive = new Location(4, 3);
			HashSet<Location> movesFive = bpFive.getPossibleMovements(gbFive);
			assertTrue(movesFive.contains(newMoveFive));
			
			GameBoard gbSix = new GameBoard();
			BasicPiece bpSix = new BasicPiece( new Location(4,4), "Red");
			BasicPiece bpOppSix = new BasicPiece( new Location(3,4), "Blue");
			gbSix.addPiece(bpSix);
			gbSix.addPiece(bpOppSix);
			Location newMoveSix = new Location(3, 4);
			HashSet<Location> movesSix = bpSix.getPossibleMovements(gbSix);
			assertTrue(movesSix.contains(newMoveSix));
			
			GameBoard gbSeven = new GameBoard();
			BasicPiece bpSeven = new BasicPiece( new Location(4,4), "Red");
			BasicPiece bpOppSeven = new BasicPiece( new Location(4,5), "Blue");
			gbSeven.addPiece(bpSeven);
			gbSeven.addPiece(bpOppSeven);
			Location newMoveSeven = new Location(4, 5);
			HashSet<Location> movesSeven = bpSeven.getPossibleMovements(gbSeven);
			assertTrue(movesSeven.contains(newMoveSeven));
			
		}
		
		@Test
		public void BasicPieceReturnType() {
			
			BasicPiece bp = new BasicPiece( new Location(5, 8), "Red");
			assertEquals( bp.pieceType(), "basicpiece");
			assertEquals(bp.gameName(), "Trooper");
		}



		@Test
		public void Diagonal() {
			
			GameBoard gb = new GameBoard();
			Diagonal diag = new Diagonal(new Location( 5, 6), "Red");
			assertEquals(diag.getLocation().getRow(), 5);
			assertEquals(diag.getLocation().getCol(), 6);
			
			Diagonal diagTwo = new Diagonal(new Location( 5, 9), "Blue");
			assertEquals( gb.isStartingZone("Blue", diagTwo.getLocation()), false);
			
			BasicPiece diagThree = new BasicPiece(new Location( 14, 0), "Blue");
			assertEquals(diagThree.getLocation().getRow(), 14);
			assertEquals(diagThree.getLocation().getCol(), 0);
			
			BasicPiece diagFour = new BasicPiece( new Location(5, 8), "Red");
			assertEquals( gb.isStartingZone("Red", diagFour.getLocation()), true);
					
		}
		
		@Test
		public void DiagonalGetMovement() {
			
			GameBoard gbFour = new GameBoard();
			Diagonal pFour = new Diagonal( new Location(4,4), "Red");
			Diagonal pOppFour = new Diagonal( new Location(3,3), "Blue");
			gbFour.addPiece(pFour);
			gbFour.addPiece(pOppFour);
			Location newMoveFour = new Location(3, 3);
			HashSet<Location> movesFour = pFour.getPossibleMovements(gbFour);
			assertTrue(movesFour.contains(newMoveFour));
			
			GameBoard gbFive = new GameBoard();
			Diagonal pFive = new Diagonal( new Location(4,4), "Red");
			Diagonal pOppFive = new Diagonal( new Location(5,3), "Blue");
			gbFive.addPiece(pFive);
			gbFive.addPiece(pOppFive);
			Location newMoveFive = new Location(5, 3);
			HashSet<Location> movesFive = pFive.getPossibleMovements(gbFive);
			assertTrue(movesFive.contains(newMoveFive));
			
			GameBoard gbSix = new GameBoard();
			Diagonal pSix = new Diagonal( new Location(4,4), "Red");
			Diagonal pOppSix = new Diagonal( new Location(5,5), "Blue");
			gbSix.addPiece(pSix);
			gbSix.addPiece(pOppSix);
			Location newMoveSix = new Location(5, 5);
			HashSet<Location> movesSix = pSix.getPossibleMovements(gbSix);
			assertTrue(movesSix.contains(newMoveSix));
			
			GameBoard gbSeven = new GameBoard();
			Diagonal pSeven = new Diagonal( new Location(4,4), "Red");
			Diagonal pOppSeven = new Diagonal( new Location(3,5), "Blue");
			gbSeven.addPiece(pSeven);
			gbSeven.addPiece(pOppSeven);
			Location newMoveSeven = new Location(3, 5);
			HashSet<Location> movesSeven = pSeven.getPossibleMovements(gbSeven);
			assertTrue(movesSeven.contains(newMoveSeven));
			
		}
		
		@Test
		public void DiagonalReturnType() {
			
			Diagonal diagp = new Diagonal( new Location(5, 8), "Red");
			assertEquals( diagp.pieceType(), "diagonal");
		}

		@Test
		public void LongRangePiece() {
			
			GameBoard gb = new GameBoard();
			LongRange lr = new LongRange(new Location( 5, 7), "Red");
			assertEquals(lr.getLocation().getRow(), 5);
			assertEquals(lr.getLocation().getCol(), 7);
			
		    LongRange lrtwo = new LongRange(new Location( 5, 9), "Blue");
			assertEquals( gb.isStartingZone("Blue", lrtwo.getLocation()), false);
			
			LongRange lrthree = new LongRange(new Location( 13, 0), "Blue");
			assertEquals(lrthree.getLocation().getRow(), 13);
			assertEquals(lrthree.getLocation().getCol(), 0);
			
			LongRange lrfour = new LongRange( new Location(11, 5), "Blue");
			assertEquals( gb.isStartingZone("Blue", lrfour.getLocation()), false);
					
		}
		
		@Test
		public void LongRangeGetMovement() {
			
			
			GameBoard gbFour = new GameBoard();
			LongRange bpFour = new LongRange( new Location(4,4), "Red");
			LongRange bpOppFour = new LongRange( new Location(5,4), "Blue");
			gbFour.addPiece(bpFour);
			gbFour.addPiece(bpOppFour);
			Location newMoveFour = new Location(5, 4);
			HashSet<Location> movesFour = bpFour.getPossibleMovements(gbFour);
			assertTrue(movesFour.contains(newMoveFour));
			
			GameBoard gbFive = new GameBoard();
			LongRange bpFive = new LongRange( new Location(4,4), "Red");
			LongRange bpOppFive = new LongRange( new Location(4,3), "Blue");
			gbFive.addPiece(bpFive);
			gbFive.addPiece(bpOppFive);
			Location newMoveFive = new Location(4, 3);
			HashSet<Location> movesFive = bpFive.getPossibleMovements(gbFive);
			assertTrue(movesFive.contains(newMoveFive));
			
			GameBoard gbSix = new GameBoard();
			LongRange bpSix = new LongRange( new Location(4,4), "Red");
			LongRange bpOppSix = new LongRange( new Location(3,4), "Blue");
			gbSix.addPiece(bpSix);
			gbSix.addPiece(bpOppSix);
			Location newMoveSix = new Location(3, 4);
			HashSet<Location> movesSix = bpSix.getPossibleMovements(gbSix);
			assertTrue(movesSix.contains(newMoveSix));
			
			GameBoard gbSeven = new GameBoard();
			LongRange bpSeven = new LongRange( new Location(4,4), "Red");
			LongRange bpOppSeven = new LongRange( new Location(4,5), "Blue");
			gbSeven.addPiece(bpSeven);
			gbSeven.addPiece(bpOppSeven);
			Location newMoveSeven = new Location(4, 5);
			HashSet<Location> movesSeven = bpSeven.getPossibleMovements(gbSeven);
			assertTrue(movesSeven.contains(newMoveSeven));
			
		}
		
		@Test
		public void LongRangeReturnType() {
			
			LongRange lr = new LongRange( new Location(5, 8), "Red");
			assertEquals( lr.pieceType(), "longrange");
		}

		@Test
		public void TeleporterPiece() {
			
			GameBoard gb = new GameBoard();
			Teleporter tp = new Teleporter(new Location( 5, 7), "Red");
			gb.addPiece(tp);
			assertEquals(tp.getLocation().getRow(), 5);
			assertEquals(tp.getLocation().getCol(), 7);
			
		    Teleporter tltwo = new Teleporter(new Location( 5, 9), "Blue");
		    gb.addPiece(tltwo);
			assertEquals( gb.isStartingZone("Blue", tltwo.getLocation()), false);
			
			Teleporter tlthree = new Teleporter(new Location( 13, 0), "Blue");
			gb.addPiece(tlthree);
			assertEquals(tlthree.getLocation().getRow(), 13);
			assertEquals(tlthree.getLocation().getCol(), 0);
			
			Teleporter tlfour = new Teleporter( new Location(11, 5), "Blue");
			gb.addPiece(tlfour);
			assertEquals( gb.isStartingZone("Blue", tlfour.getLocation()), false);
					
		}
		
		@Test
		public void TeleporterGetMovement() {
			
			GameBoard gbZero = new GameBoard();
			Teleporter bpZero = new Teleporter( new Location(4,4), "Red");
			Teleporter bpOppZero = new Teleporter( new Location(5,4), "Blue");
			gbZero.addPiece(bpZero);
			gbZero.addPiece(bpOppZero);
			Location newMoveZero = new Location(5, 4);
			HashSet<Location> movesZero = bpZero.getPossibleMovements(gbZero);
			assertTrue(movesZero.contains(newMoveZero));
			
			
		}
		
	    @Test
		public void TeleporterReturnType() {
			
			Teleporter tr = new Teleporter( new Location(5, 8), "Red");
			assertEquals( tr.pieceType(), "teleporter");
		}
		
		@Test
		public void HopperPiece() {
			
			GameBoard gb = new GameBoard();
			Hopper hp = new Hopper(new Location( 5, 7), "Red");
			gb.addPiece(hp);
			assertEquals(hp.getLocation().getRow(), 5);
			assertEquals(hp.getLocation().getCol(), 7);
			
			Hopper hptwo = new Hopper(new Location( 5, 9), "Blue");
			gb.addPiece(hptwo);
			assertEquals( gb.isStartingZone("Blue", hptwo.getLocation()), false);
			
			Hopper hpthree = new Hopper(new Location( 13, 0), "Blue");
			gb.addPiece(hpthree);
			assertEquals(hpthree.getLocation().getRow(), 13);
			assertEquals(hpthree.getLocation().getCol(), 0);
			
			Hopper hpfour = new Hopper( new Location(11, 5), "Blue");
			gb.addPiece(hpfour);
			assertEquals( gb.isStartingZone("Blue", hpfour.getLocation()), false);
					
		}
		
		@Test
		public void HopperGetMovement() {
			
			GameBoard gbFour = new GameBoard();
			Hopper bpFour = new Hopper( new Location(4,4), "Red");
			Hopper bpOppFour = new Hopper( new Location(5,4), "Blue");
			gbFour.addPiece(bpFour);
			gbFour.addPiece(bpOppFour);
			Location newMoveFour = new Location(6, 4);
			HashSet<Location> movesFour = bpFour.getPossibleMovements(gbFour);
			assertTrue(movesFour.contains(newMoveFour));
			
			GameBoard gbFive = new GameBoard();
			Hopper bpFive = new Hopper( new Location(4,4), "Red");
			Hopper bpOppFive = new Hopper( new Location(4,3), "Blue");
			gbFive.addPiece(bpFive);
			gbFive.addPiece(bpOppFive);
			Location newMoveFive = new Location(4, 2);
			HashSet<Location> movesFive = bpFive.getPossibleMovements(gbFive);
			assertTrue(movesFive.contains(newMoveFive));
			
			GameBoard gbSix = new GameBoard();
			Hopper bpSix = new Hopper( new Location(4,4), "Red");
			Hopper bpOppSix = new Hopper( new Location(3,4), "Blue");
			gbSix.addPiece(bpSix);
			gbSix.addPiece(bpOppSix);
			Location newMoveSix = new Location(2, 4);
			HashSet<Location> movesSix = bpSix.getPossibleMovements(gbSix);
			assertTrue(movesSix.contains(newMoveSix));
			
			GameBoard gbSeven = new GameBoard();
			Hopper bpSeven = new Hopper( new Location(4,4), "Red");
			Hopper bpOppSeven = new Hopper( new Location(4,5), "Blue");
			gbSeven.addPiece(bpSeven);
			gbSeven.addPiece(bpOppSeven);
			Location newMoveSeven = new Location(4, 6);
			HashSet<Location> movesSeven = bpSeven.getPossibleMovements(gbSeven);
			assertTrue(movesSeven.contains(newMoveSeven));
			
		}
		
		@Test
		public void HopperReturnType() {
			
			Hopper lr = new Hopper( new Location(5, 8), "Red");
			assertEquals( lr.pieceType(), "hopper");
			assertEquals(lr.gameName(), "Horseman");
		}
		
		@Test
		public void testPWIndicators()
		{
			BasicPiece bp = new BasicPiece(new Location(5,5), "Red");
			Swapper sw = new Swapper(8,3);
			Flag fl = new Flag(1,10);
			bp.changeIfFlagZone();
			bp.changeIsClone();
			bp.activatePowerup(sw);
			assertEquals(bp.getCurrentPowerup(), sw);
			bp.deactivatePowerup();			
			assertFalse(bp.hasPowerup());
			bp.getFlag();
			bp.giveFlag(fl);
			bp.removeFlag();
			assertFalse(bp.getHasFlag());
			assertFalse(bp.canThrowFlag());
			
			BasicPiece bpTwo = new BasicPiece( new Location(7,5), "Red");
			assertFalse(bpTwo.isClone());
			assertFalse(bpTwo.isInFlagZone());
			
			
			
		}
		
		@Test
		public void FlagTest()
		{
			Flag fl = new Flag(1,10);
			assertEquals(fl.gameName(), "Flag");
		}
		
		@Test
		public void Clone() {
			BasicPiece bp = new BasicPiece(new Location(17,0), "Red");
			LongRange lr = new LongRange(new Location(17,1), "Red");
			Hopper hr = new Hopper(new Location(17,2), "Red");
			Diagonal dl = new Diagonal(new Location(17,3), "Red");
			Teleporter tp = new Teleporter(new Location(17,4), "Red");
			//BasicPiece bpTwo = new BasicPiece( new Location(17,0), "Red");
			//LinkedList<Piece> list = new LinkedList<Piece>();
			//list.add(bp);
			//list.add(bpTwo);
			Clone cl = new Clone(9,4);
			GameBoard gb = new GameBoard();
			gb.addPiece(bp);
			//gb.addPiece(bpTwo);
			gb.addPowerupToBoard(cl, new Location(9,4));
			assertFalse(cl.effect(bp));
			
			cl.getClone(bp, new Location(4,2));
			cl.getClone(lr, new Location(4,3));
			cl.getClone(hr, new Location(4,4));
			cl.getClone(dl, new Location(4,5));
			cl.getClone(tp, new Location(4,6));
			
			
			cl.deactivate(bp);
			
			
			assertFalse(cl.effect(bp));
			
			assertEquals( cl.powerupType(), "clone");
			
			assertEquals( cl.gameName(),  "Clone");
		}
		
		@Test
		public void HighJump() {
			GameBoard gb = new GameBoard();
			LongRange lr = new LongRange(new Location(6,8), "Red");
			LongRange test = new LongRange(new Location(9,8), "Blue");
			HighJump hj = new HighJump(9,8);
			gb.addPowerupToBoard(hj, new Location(9,8));
			gb.addPiece(lr);
			gb.addPiece(test);
			hj.effect(lr);
			assertTrue( hj.getTurnsLeft() == 3);
			hj.decrementTurns();
			assertTrue(hj.getTurnsLeft() == 2);
			Location newMove = new Location(10,8);
			HashSet<Location> moves = lr.getPossibleMovements(gb);
			assertTrue(moves.contains(newMove));
			
			hj.deactivate(lr);
			if(lr.hasHighJump())
			{
				//System.out.println("a");
			}
			//check why this line doesnt work with blue
			BasicPiece bpTest = new BasicPiece(new Location(6,9), "Red");
			gb.addPiece(bpTest);
			Location newMoveTwo = new Location(6, 10);
			HashSet<Location> movesTwo = lr.getPossibleMovements(gb);
			//System.out.println(movesTwo.toString());
			assertFalse( movesTwo.contains(newMoveTwo));
			
			assertEquals( hj.powerupType(), "highjump");
			
			assertEquals( hj.gameName(),  "Air Jordan");
			
			
			
			
		}
		
		@Test
		public void HighWall() {

			GameBoard gb = new GameBoard();
			Hopper hr = new Hopper(new Location(13,8), "Red");
			BasicPiece bpRed = new BasicPiece(new Location(10,0), "Red");
			LinkedList<Piece> redPieces = new LinkedList<Piece>();
			redPieces.add(hr);
			redPieces.add(bpRed);
			BasicPiece block = new BasicPiece(new Location(14,8), "Blue");
			HighWall hw = new HighWall( 10, 5);
			gb.addPowerupToBoard(hw, new Location(10,5));
			gb.addPiece(hr);
			gb.addPiece(block);
			assertFalse(hw.effect(hr));
			hw.effect(redPieces);
			Location newMove = new Location(15,8);
			HashSet<Location> moves = hr.getPossibleMovements(gb);
			//System.out.println(moves.toString());
			assertFalse(moves.contains(newMove));
			
			hw.deactivate(hr);
			
			BasicPiece bpTest = new BasicPiece(new Location(13,9), "Blue");
			gb.addPiece(bpTest);
			Location newMoveTwo = new Location(13,10);
			hr.getPossibleMovements(gb);
			HashSet<Location> movesTwo = hr.getPossibleMovements(gb);
			assertTrue(movesTwo.contains(newMoveTwo));
			
			assertEquals( hw.powerupType(), "highwall");
			
			assertEquals( hw.gameName(),  "High Gravity");
			
			
			
		}

		@Test
		public void Invincibility() {
			
			GameBoard gb = new GameBoard();
			BasicPiece bpOne = new BasicPiece(new Location(7,5), "Red");
			BasicPiece bpTwo = new BasicPiece(new Location(8,5), "Blue");
			Invincibility iy = new Invincibility(9,14);
			gb.addPowerupToBoard(iy, new Location(9,14));
			gb.addPiece(bpOne);
			gb.addPiece(bpTwo);
			iy.effect(bpTwo);
			Location newMove = new Location(8,5);
			HashSet<Location> moves = bpOne.getPossibleMovements(gb);
			//System.out.println(moves.toString());
			assertFalse(moves.contains(newMove));
			
			iy.deactivate(bpTwo);
			
			HashSet<Location> movesTwo = bpOne.getPossibleMovements(gb);
			assertTrue(movesTwo.contains(newMove));
			
			assertEquals( iy.powerupType(), "invincibility");
			
			assertEquals( iy.gameName(),  "Invincibility Star");
		}
		
		@Test
		public void Invisibility() {
			BasicPiece bp = new BasicPiece(new Location(15,8), "Red");
			Invisibility iv = new Invisibility(9,4);
			GameBoard gb = new GameBoard();
			gb.addPiece(bp);
			gb.addPowerupToBoard(iv, new Location(9,4));
			assertTrue(iv.effect(bp));
			
			iv.deactivate(bp);
			
			assertEquals( iv.powerupType(), "invisibility");
			
			assertEquals( iv.gameName(),  "Invisibility");
			
		}
		
		@Test
		public void JailBreak() {
			BasicPiece bp = new BasicPiece(new Location(17,1), "Red");
			BasicPiece bpTwo = new BasicPiece( new Location(17,0), "Red");
			LinkedList<Piece> list = new LinkedList<Piece>();
			list.add(bp);
			list.add(bpTwo);
			Jailbreak jb = new Jailbreak(9,4);
			GameBoard gb = new GameBoard();
			gb.addPiece(bp);
			gb.addPiece(bpTwo);
			gb.addPowerupToBoard(jb, new Location(9,4));
			assertFalse(jb.effect(bp));
			
			jb.deactivate(bp);
			
			
			assertTrue(jb.effect(list));
			
			assertEquals( jb.powerupType(), "jailbreak");
			
			assertEquals( jb.gameName(),  "Get out of jail card");
			
		}
		
		@Test
		public void MoveBoost() {
			
			GameBoard gb = new GameBoard();
			BasicPiece bp = new BasicPiece(new Location(15,3), "Blue");
			MoveBoost mb = new MoveBoost(11,8);
			gb.addPiece(bp);
			gb.addPowerupToBoard(mb, new Location(11,8));
			mb.effect(bp);
			Location newMove = new Location(12,3);
			HashSet<Location> moves = bp.getPossibleMovements(gb);
			assertTrue(moves.contains(newMove));
			
			mb.deactivate(bp);

			HashSet<Location> movesTwo = bp.getPossibleMovements(gb);
			assertFalse(movesTwo.contains(newMove));
			
			assertEquals( mb.powerupType(), "moveboost");
			
			assertEquals( mb.gameName(),  "Move Boost");
			
		}
		
		@Test
		public void Paralysis() {
			
			GameBoard gb = new GameBoard();
			BasicPiece bp = new BasicPiece(new Location(15,10), "Blue");
			BasicPiece bpTwo = new BasicPiece( new Location(14,7), "Red");
			BasicPiece bpThree = new BasicPiece( new Location(16,10), "Red");
			Paralysis ps = new Paralysis(11,8);
			gb.addPiece(bp);
			gb.addPiece(bpTwo);
			gb.addPiece(bpThree);
			gb.addPowerupToBoard(ps, new Location(11,8));
			assertFalse(ps.effect(bp));
			Queue<Piece> paralyzed = ps.getSurrounding(bp, gb);
			assertTrue(paralyzed.size() == 3);	
			while( !paralyzed.isEmpty())
			{
				Piece p = paralyzed.remove();
				if( p.getSide().equals("Red"))
				{
					ps.effectEnemy(p);
				}
			}	
		    System.out.println(bpTwo.isParalysed());
			assertTrue(bpTwo.getPossibleMovements(gb).size() == 1);
			
			ps.deactivate(bpTwo);
			assertFalse(bpTwo.getPossibleMovements(gb).isEmpty());
			
			assertEquals( ps.powerupType(), "paralysis");
			
			assertEquals( ps.gameName(),  "Paralyzer");

			
		}
		
		@Test
		public void RoleReversal() {
			
			GameBoard gb = new GameBoard();
			LongRange lr = new LongRange(new Location(11,6), "Blue");
			BasicPiece bp = new BasicPiece( new Location(6,6), "Red");
			RoleReversal rl = new RoleReversal(8,9);
			gb.addPiece(lr);
			gb.addPiece(bp);
			gb.addPowerupToBoard(rl, new Location(8,9));
			rl.effect(lr);
			Location newMove = new Location(6,6);
			HashSet<Location> moves = lr.getPossibleMovements(gb);
			assertTrue(moves.contains(newMove));
			
			rl.deactivate(lr);
			HashSet<Location> movesTwo = lr.getPossibleMovements(gb);
			assertFalse( movesTwo.contains(newMove));
			
			assertEquals( rl.powerupType(), "rolereversal");
			
			assertEquals( rl.gameName(),  "Role Reversal");
			
			
			
		}
		
		@Test
		public void Swapper() {
			GameBoard gb = new GameBoard();
			BasicPiece bp = new BasicPiece( new Location(10,12), "Blue");
			BasicPiece bpTwo = new BasicPiece( new Location(13, 4), "Blue");
			Swapper sw = new Swapper(10,4);
			gb.addPiece(bp);
			gb.addPiece(bpTwo);
			gb.addPowerupToBoard(sw, new Location(10,4));
			assertFalse(sw.effect(bp));
			sw.effect(bp, bpTwo);
			Location loc1 = new Location(10,12);
			Location loc2 = new Location(13, 4);
			assertEquals( bp.getLocation(), loc2);
			assertEquals( bpTwo.getLocation(), loc1);
			
			sw.deactivate(bp);
			
			assertEquals( sw.powerupType(), "swapper");
			
			assertEquals( sw.gameName(),  "Swapper");
			
		}
		
		@Test
		public void TagRadius() {
			GameBoard gb = new GameBoard();
			BasicPiece bp = new BasicPiece(new Location(17,2), "Red");
			BasicPiece bpTwo = new BasicPiece( new Location(17,0), "Red");
			//LinkedList<Piece> list = new LinkedList<Piece>();
			//list.add(bp);
			//list.add(bpTwo);
			TagRadius tr = new TagRadius(9,4);
			gb.addPiece(bp);
			gb.addPiece(bpTwo);
			bp.moveTo(17,1);
			gb.addPowerupToBoard(tr, new Location(9,4));
			assertTrue(tr.effect(bp));
			
			tr.deactivate(bp);
			
			
			assertTrue( tr.rarity() == 0.125 );
			
			assertEquals( tr.powerupType(), "tagradius");
			
			assertEquals( tr.gameName(),  "Tag Radius");
			
					
			
		}
		
		@Test
		public void Throw() {
			
			GameBoard gb = new GameBoard();
			BasicPiece bp = new BasicPiece(new Location(17,1), "Red");
			BasicPiece bpTwo = new BasicPiece( new Location(17,0), "Red");
			//LinkedList<Piece> list = new LinkedList<Piece>();
			//list.add(bp);
			//list.add(bpTwo);
			Throw tw = new Throw(9,4);
			gb.addPiece(bp);
			gb.addPiece(bpTwo);
			gb.addPowerupToBoard(tw, new Location(9,4));
			assertTrue(tw.effect(bp));
			
			tw.deactivate(bp);
			
			
			assertTrue( tw.rarity() == 0.125 );
			
			assertEquals( tw.powerupType(), "throw");
			
			assertEquals( tw.gameName(),  "Quarterback");
			
		}
		
		@Test
		public void GameBoardTest()
		{
			GameBoard gb = new GameBoard();
			BasicPiece bp = new BasicPiece(new Location(5,8), "Red");
			BasicPiece bpTwo = new BasicPiece(new Location(8,5), "Red");
			BasicPiece bpThree = new BasicPiece( new Location(5, 15), "Red");
			BasicPiece bpFour = new BasicPiece( new Location(6,17), "Blue");
			Throw tw = new Throw(9,4);
			gb.addPiece(bp);
			gb.sendPieceToJail(bpThree);
			gb.blueJailBreak();
			gb.sendPieceToJail(bpFour);
			gb.redJailBreak();
			gb.movePiece(bp, new Location(6,8));
			gb.swapLocations(bp, bpTwo);
			assertFalse(gb.isLandingZone("Red", 7, 8));
			gb.addPowerup(tw, "Red");
			gb.clearSpot(7, 11);
			gb.moveBlueFlag(new Location(16,7));
			gb.moveRedFlag(new Location(1,14));
			gb.getBlueFlag();
			gb.getBlueFlag();
			gb.getBlueJail();
			gb.getBluePieces();
			gb.getBluePowerups();
			gb.getPowerups();
			gb.getRedFlag();
			gb.getRedFlagZone();
			gb.getRedJail();
			gb.getRedPieces();
			gb.getRedPowerups();
			
		}
		
		@Test
		public void ColorGuard()
		{
			Colorguard cg = new Colorguard("Red");
			BasicPiece bp = new BasicPiece(new Location(4,14), "Red");
			LongRange lr = new LongRange(new Location(4,15), "Red");
			Hopper hr = new Hopper(new Location(4,16), "Red");
			Teleporter tp = new Teleporter(new Location(4,17), "Red");
			Diagonal dl = new Diagonal( new Location(4,18), "Red");
			BasicPiece bpTwo = new BasicPiece( new Location(6,9), "Red");
			
			cg.startGame();
			assertTrue(cg.getTurns() == 1);
			cg.addPiece(new Location(4,14), bp.pieceType());
			cg.addPiece(new Location(4,15), lr.pieceType());
			cg.addPiece(new Location(4,16), hr.pieceType());
			cg.addPiece(new Location(4,17), tp.pieceType());
			cg.addPiece(new Location(4,18), dl.pieceType());
			cg.addPiece(bpTwo);
			
			HashSet<Location> pos = cg.getStartingPoints();
			HashSet<Location> landSpots = cg.getLandingSpots();
			
			assertFalse(pos.isEmpty());
			assertFalse(landSpots.isEmpty());
			
			HashSet<Location> posPieces = cg.getPossiblePieces();
			assertTrue(posPieces.isEmpty());
			
			bp.getFlag();
			Location flHolder = cg.getFlagHolder();
			//assertEquals( bp.getLocation(), flHolder);
			
			HashSet<Location> move = cg.getPossibleMovements(lr.getLocation());
			assertFalse(move.isEmpty());
			
			cg.addNewPowerup();
			cg.jailbreak();
			cg.getCurrentPowerups();
			cg.movePiece( bp.getLocation(), new Location(6, 14) );
			cg.pieceMoved(bp);
			cg.getMovedPieces();
			cg.mySide();
			cg.setMySide("Blue");
			cg.getBoard();
			cg.getCurrentTeamPieces();
			assertTrue( cg.isMoved(bp));
			cg.nextTurn();
			cg.gameOver();
			cg.getBlueTurns();
			cg.getRedTurns();
			cg.getCurrentTurn();
			cg.getOpposingTurn();
			Swapper sw = new Swapper(8,4);
			sw.effect(lr);
			//cg.useSwapper(sw, lr.getLocation(), bp.getLocation());
			Clone cn = new Clone(9,1);
			cn.effect(dl);
			cg.useClone(cn, dl.getLocation(), new Location(0,5));
			HighWall hw = new HighWall(0,1);
			cg.useHighWall(hw);
			Paralysis pl = new Paralysis(9, 14);
			cg.useParalysis(pl, tp.getLocation());
			//Throw tw = new Throw(8, 15);
			//cg.useThrow(tw, new Location(18,10), new Location(6,10));
			cg.nextPowerup();
			
			//cg.nextTurn();
			//HashSet<Location> throwOps = cg.getThrowOptions();
			//System.out.print(throwOps);
		    //assertTrue(throwOps.isEmpty());
		
			
			
		}
		

}