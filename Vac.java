/*
 ******************************************************
 *This is simulation of how a vacuum cleaner can move *
 *Through a pre defined space from the user           *
 *The Work Space is initialized in a Random way       *
 *The number of dirts and obstacles generated randomly*
 *The Start point of the Vacuum is randomly generated *
 *The direction of the vacuum generated randomly      *
 ******************************************************
 *This work is done as an assignment for:             *
 *Course: Intelligent Agent Development Course.       *
 *Dr. Yuhanis Yousof                                  *
 *By: TAHA TALEB RAGHEB ALHERSH(802396)               *
 ******************************************************
 */

import java.util.*;
import javax.swing.*;

//Class Vac The Main Class
public class Vac{
    public  int north=0,south=1,east=2,west=3;
    public int heading,curX,curY;
    public int [][] WorkArea;
    public int [][] Moves;

    //The Constructor
    public Vac(){

    }//constructor Vac
    // *** The Main Method
   	public static void main (String args[]){
		Vac vac = new Vac();
        vac.initPlace();
	}//main
/*
Initializing the work space Randomly
 * ****************
 *initPlace Method*
 * ****************
 */
    public void initPlace(){
                /*
         Following sample shows the direction in the array

               North = 0
        +----+----+----+----+
        |    |    |    |    |
        +----+----+----+----+
West = 3|    |    |    |    |East =2
        +----+----+----+----+
        |    |    |    |    |
        +----+----+----+----+
               South =1

         */


        Vac vac = new Vac();
        //Initialize the heading of the Vacuum Randomly
        heading = vac.generateRnd(4);
		int x = Integer.parseInt(JOptionPane.showInputDialog("Enter The Number of rows "));
		int y = Integer.parseInt(JOptionPane.showInputDialog("Enter The Number of columns "));
        
		int rndI,rndJ,dirtNo,obNo;
        //Make Instances for the Work Area array that the agent move through
        WorkArea = new int [x][y];
        //Store the movements of the Agent
        Moves = new int [x][y];
        //Print the array in its i,j pairs
		for ( int i = 0 ; i < x ; i++){
			for(int j = 0 ; j < y ; j++){
				System.out.print(i+","+j+"	");
                //All areas in the arry are available = 2
                WorkArea[i][j] = 2;
                //All movements are available =0
                Moves[i][j] = 0;
			}//j

				System.out.println("");
		}//i
          //Generate the Number of the dirts and obstacles
        //We check the greater dimension x of the array to generate the maximum number of dirts and
            //obstacles
		if (x>y){
            
            dirtNo = vac.generateRnd(x);
			obNo = vac.generateRnd(x);
            for (int k=0 ; k < dirtNo; k++){
                //the places of the dirts are Randomly generated and set to 0 in the array
                WorkArea[vac.generateRnd(x)][vac.generateRnd(y)]=0;
                
            }//for
            for (int m=0 ; m < obNo; m++){
                //the places of the obstacles are Randomly generated and set to 1 in the array
                WorkArea[vac.generateRnd(x)][vac.generateRnd(y)]=1;
            }//for
            //Generate the first place of the agent randomly and it should be in an available place = 2
            do {
                rndI = vac.generateRnd(x);
                rndJ = vac.generateRnd(y);
            }while (WorkArea[rndI][rndJ] != 2);
		}//if
        //We check the greater dimension y of the array to generate the maximum number of dirts and
            //obstacles
		else {
			dirtNo = vac.generateRnd(y);
			obNo = vac.generateRnd(y);
            for (int k=0 ; k < dirtNo; k++){
                 //the places of the dirts are Randomly generated and set to 0 in the array
                WorkArea[vac.generateRnd(x)][vac.generateRnd(y)]=0;
            }//for
            for (int m=0 ; m < obNo; m++){
                //the places of the obstacles are Randomly generated and set to 1 in the array
                WorkArea[vac.generateRnd(x)][vac.generateRnd(y)]=1;
            }//for
            do {
                 //Generate the first place of the agent randomly and it should be in an available place = 2
                rndI = vac.generateRnd(x);
                rndJ = vac.generateRnd(y);
            }while (WorkArea[rndI][rndJ] != 2);
		}//else

        curX = rndI;
        curY = rndJ;
        // The place that the agent will be in is visited and haveto be set to 1
        // That its is the first place that the agent move to.
		Moves[curX][curY]=1;
        //print the number of dirts
		System.out.println("Random Dirt Number "+dirtNo);
        //print the number of Obstacles
		System.out.println("Random Obstcale Number "+obNo);
        //The dirt place can be replaced the obstacle one and vise virsa
//Print the values of the work area array of the agent
        for ( int i = 0;i<x;i++){
			for(int j =0; j<y ; j++){
				System.out.print(WorkArea[i][j]+"   ");
                
			}//j

				System.out.println("");
		}//i
        // print the first position that the agent will be in
        System.out.println("The position of the agent at (" + rndI+","+rndJ+") = "+ WorkArea[rndI][rndJ]);
        // print the first heading of the agent
        System.out.println("The Agent Heading "+heading);
        // Call the detectOb (Detect Obstacle) method to start checking the obstacles)
        vac.detectOb(rndI, rndJ,heading,WorkArea,Moves);


    }//initPlace
/*
 Generating the random numbers
 * ********************
 * generateRnd Method *
 * ********************
 */
	public int generateRnd(int n){
		Random rnd = new Random();
		return rnd.nextInt(n);

	}//generateRnd
 /*
Detecting the Obstacles and the Dirts
 * ********************
 * detectOb Method    *
 * ********************
 */
    public void detectOb(int x,int y, int h,int wrk[][],int mvs[][]){
    // Print the movements for the agent to see the progress of the agent 
    for ( int i = 0;i<mvs.length;i++){
			for(int j =0; j<mvs[i].length ; j++){
				System.out.print(mvs[i][j]+"   ");

			}//j

				System.out.println("");
	}//i
        Vac vac = new Vac();
        vac.curX = x;
        vac.curY = y;
    // Check the status of the agent if it stucked or can move
    if (vac.stuck(x, y, h, wrk, mvs)){
        // If the agent is stucked it will be print the following
            System.out.println("STUCKED");
        }//If Stuck
    //If the agent is not stucked and can move
    else{
    // Depending on the heading of the agent the action will be performed
        switch(h){
            // If the heading of the agent is to the Noth = 0
            case 0 :
                // If the agent is in the first row of the array that the value of x always 0
                if (x == 0){
                    // Change the heading becuase if the agent moves in its direction will
                    // be out of the boundries of the array
                    h = vac.changeHead(h);
                    // After changing the direction it should detect the obstacles for the
                    // new direction
                    vac.detectOb(x, y, h, wrk,mvs);
                }//if
                else{
                    // If the  agent is in the last row of the array and facing Obstacles
                    if (wrk[x - 1][y] == 1 ){
                            //The position of the Obstacle is set to 1 that it is visited
                            mvs[x-1][y]=1;
                            //Change the heading of the agent becuase it is facing Obstacle
                            h = vac.changeHead(h);
                            //After changing the direction it should detect the obstacles for the
                            // new direction
                            vac.detectOb(x, y, h, wrk,mvs);
                    }//if

                    else{
                        // If the  agent is in the last row of the array and has Dirt
                        if (wrk[x][y] == 0 ){
                            //Calling the clear method to clear the dirt
                            vac.clear(x, y, h, wrk,mvs);
                        }//if
                        else{
                            //Check if the next move in the same direction is visited or not
                            //through the visited method
                            if (vac.visited(x-1, y, mvs)){
                                //If the visited method returns true that mean that this position is visited
                                //then the agent should change his direction
                                h = vac.changeHead(h);
                                //After changing the direction it should detect the obstacles for the
                                // new direction
                                vac.detectOb(x, y, h, wrk, mvs);
                            }//If visited
                            else
                                //Calling the move method to move from the current position to other
                                //position
                                vac.move(x, y, h,wrk,mvs);
                        }
                    }//else
                        
                }//else
                break;
            // If the heading of the agent is to the South = 1
            case 1:
                //If the agent is the last row of the array
                if ((x == wrk.length -1)){
                    //Change the heading of the agent becuase it is in the last row
                    // and heading south the next move will be out od array boundaries
                    h = vac.changeHead(h);
                    //After changing the direction it should detect the obstacles for the
                    // new direction
                    vac.detectOb(x, y, h, wrk,mvs);
                }//if
                else{
                    // If the agent is facing an Obstacle
                    if (wrk[x + 1][y] == 1 ){
                        //The position of the Obstacle is set to 1 that it is visited
                        mvs[x+1][y]=1;
                        //Change the heading of the agent becuase it is facing Obstacle
                        h = vac.changeHead(h);
                        //After changing the direction it should detect the obstacles for the
                        // new direction
                        vac.detectOb(x, y, h, wrk,mvs);
                    }//if
                    else{
                        //If the agent position has dirt
                        if (wrk[x][y] == 0 ){
                            //The Method clear is called
                            vac.clear(x, y, h, wrk,mvs);
                        }//if
                        else{
                            //Check if the next move in the same direction is visited or not
                            //through the visited method
                            if (vac.visited(x+1, y, mvs)){
                                //Change the heading of the agent becuase it is facing a visited position
                                h = vac.changeHead(h);
                                //After changing the direction it should detect the obstacles for the
                                // new direction
                                vac.detectOb(x, y, h, wrk, mvs);
                            }
                            else
                                //Calling the move method to move from the current position to other
                                //position
                                vac.move(x, y, h,wrk,mvs);
                        }
                    }//else
                        
                }//else
                    break;

            // If the heading of the agent is to the East = 2
            case 2 :
                //If the agent is the last Column of the array
                if ((y == (wrk[x].length -1))){
                    //Change the heading of the agent becuase it is in the last row
                    // and heading south the next move will be out od array boundaries
                    h = vac.changeHead(h);
                    //After changing the direction it should detect the obstacles for the
                    // new direction
                    vac.detectOb(x, y, h, wrk,mvs);
                }//if
                else{
                    // If the agent is facing an Obstacle
                    if (wrk[x][y + 1] == 1 ){
                        //The position of the Obstacle is set to 1 that it is visited
                        mvs[x][y+1]=1;
                        //Change the heading of the agent becuase it is facing Obstacle
                        h = vac.changeHead(h);
                        //After changing the direction it should detect the obstacles for the
                        // new direction
                        vac.detectOb(x, y, h, wrk,mvs);
                    }//if
                    else{
                        //If the agent position has dirt
                        if (wrk[x][y] == 0 ){
                            //The Method clear is called
                            vac.clear(x, y, h, wrk,mvs);
                        }//if
                        else{
                            //Check if the next move in the same direction is visited or not
                            //through the visited method
                            if (vac.visited(x, y+1, mvs)){
                                //Change the heading of the agent becuase it is facing a visited position
                                h = vac.changeHead(h);
                                //After changing the direction it should detect the obstacles for the
                                // new direction
                                vac.detectOb(x, y, h, wrk, mvs);
                            }//If visisted
                            else
                                //Calling the move method to move from the current position to other
                                //position
                                vac.move(x, y, h,wrk,mvs);
                        }//else
                    }//else
                        
                }//else
                break;
            // If the heading of the agent is to the West = 3
            case 3:
                if ((y == 0)){
                    //Change the heading of the agent becuase it is in the first column
                    // and heading south the next move will be out od array boundaries
                    h = vac.changeHead(h);
                    //After changing the direction it should detect the obstacles for the
                    // new direction
                    vac.detectOb(x, y, h, wrk,mvs);
                }//if
                else{
                    // If the agent is facing an Obstacle
                    if (wrk[x][y - 1] == 1 ){
                            //The position of the Obstacle is set to 1 that it is visited
                            mvs[x][y-1]=1;
                            //Change the heading of the agent becuase it is facing Obstacle
                            h = vac.changeHead(h);
                            //After changing the direction it should detect the obstacles for the
                            // new direction
                            vac.detectOb(x, y, h, wrk, mvs);
                    }//if
                    else{
                        //If the agent position has dirt
                        if (wrk[x][y] == 0 ){
                            //The Method clear is called
                            vac.clear(x, y, h, wrk,mvs);
                        }//if
                        else{
                            //Check if the next move in the same direction is visited or not
                            //through the visited method
                            if (vac.visited(x, y-1, mvs)){
                                //Change the heading of the agent becuase it is facing a visited position
                                h = vac.changeHead(h);
                                //After changing the direction it should detect the obstacles for the
                                // new direction
                                vac.detectOb(x, y, h, wrk, mvs);
                            }
                            else
                                //Calling the move method to move from the current position to other
                                //position
                                vac.move(x, y, h,wrk,mvs);
                        }
                    }//else
                        
                }//else

                break;
        }//switch
    }//else stucked
        //vac.changeHead(h);

    }//detectOb
  /*
Change the heading of the agent Method
 * ********************
 * changeHead Method  *
 * ********************
 */
    public int changeHead(int h){
       //Vac vac = new Vac();
       // Depending on the heading of the agent the action will be performed
        switch(h){
            //If the agent is heading North =0
            case 0:
            
                System.out.println("heading changed to 2");
                return 2;

            //If the agent is heading South =1
            case 1:
            
                System.out.println("heading changed to 3");
                return 3;
            //If the agent is heading East =2
            case 2:
                
                System.out.println("heading changed to 1");
                return 1;
            //If the agent is heading West = 3
            case 3:
                
                System.out.println("heading changed to 0");
                return 0;
                
        }
        return 4;
    }//changeHead

/*
Move agent Method, the agent moves only one step to his heading
 * ********************
 * move Method        *
 * ********************
 */
    public void move(int x, int y,int h,int wrk[][],int mvs[][]){

        Vac vac = new Vac();
        //If the agent is not stucked
        if (vac.stuck(x, y, h, wrk, mvs)){
            System.out.println("STUCKED");
        }//if
        else{
        // Depending on the heading of the agent the action will be performed
        switch (h){
            // If the heading of the agent is to the NORTH = 0
            case 0:
                //The next move will be to the previous row in the array
                curX = x - 1;
                curY = y;
                //Print the current poisition of the agent
                System.out.println("Curr X0 " + curX);
                System.out.println("Curr Y0 " + curY);
                //Check if the next move in the same direction is visited or not
                //through the visited method
                if (vac.visited(x -1, y, mvs)){
                    //Change the heading of the agent becuase it is facing a visited position
                    vac.changeHead(h);
                    //After changing the direction it should detect the obstacles for the
                    // new direction
                    vac.detectOb(curX, curY, h, wrk,mvs);
                }
                else{
                    //Set the value of the current position of the agent as visited = 1
                    mvs[curX][curY]=1;
                    //detect the obstacles for the new position
                    vac.detectOb(curX, curY, h,wrk,mvs);
                    break;
                }
            // If the heading of the agent is to the South = 1
            case 1:
                // The next move will be to the next row in the array
                curX = x + 1;
                curY = y;
                //Print the current poisition of the agent
                System.out.println("Curr X1 " + curX);
                System.out.println("Curr Y1 " + curY);
                //Check if the next move in the same direction is visited or not
                //through the visited method
                if (vac.visited(x+1, y, mvs)){
                    //Change the heading of the agent becuase it is facing a visited position
                    vac.changeHead(h);
                    //After changing the direction it should detect the obstacles for the
                    // new direction
                    vac.detectOb(curX, curY, h, wrk,mvs);
                }
                else{
                    //Set the value of the current position of the agent as visited = 1
                    mvs[curX][curY]=1;
                    //detect the obstacles for the new position
                    vac.detectOb(curX, curY, h, wrk,mvs);
                    break;
                }

                //mvs[x][y+1]= 1;
            // If the heading of the agent is to the East = 2
            case 2:
                // The next move will be to next column in the array
                curX = x;
                curY = y + 1;
                //Print the current poisition of the agent
                System.out.println("Curr X2 " + curX);
                System.out.println("Curr Y2 " + curY);
                //Check if the next move in the same direction is visited or not
                //through the visited method
                if (vac.visited(x, y+1, mvs)){
                    //Change the heading of the agent becuase it is facing a visited position
                    vac.changeHead(h);
                    //After changing the direction it should detect the obstacles for the
                    // new direction
                    vac.detectOb(curX, curY, h, wrk,mvs);
                }
                else{
                    //Set the value of the current position of the agent as visited = 1
                    mvs[curX][curY]=1;
                    //detect the obstacles for the new position
                    vac.detectOb(curX, curY, h, wrk,mvs);
                    break;
                }

                //mvs[x+1][y]= 1;
            // If the heading of the agent is to the West = 3
            case 3:
                //The new position will be in the previous column
                curX = x;
                curY = y - 1 ;
                //Print the current poisition of the agent
                System.out.println("Curr X3 " + curX);
                System.out.println("Curr Y3 " + curY);
                //Check if the next move in the same direction is visited or not
                //through the visited method
                if (vac.visited(x, y-1, mvs)){
                    //Change the heading of the agent becuase it is facing a visited position
                    vac.changeHead(h);
                    //After changing the direction it should detect the obstacles for the
                    // new direction
                    vac.detectOb(curX, curY, h, wrk,mvs);
                }
                else {
                    //Set the value of the current position of the agent as visited = 1
                    mvs[curX][curY]=1;
                    //detect the obstacles for the new position
                    vac.detectOb(curX, curY, h, wrk,mvs);
                     break;
                }

                //mvs[x-1][y]= 1;
               
        }
        }
        

    }//move
/*
Check if the position passed is visited or not
 * 0 : Not visited
 * 1 : Visited
 * ********************
 * visited Method     *
 * ********************
 */
public boolean visited(int x, int y, int mvs[][]){
    //If the value is 1 so the position is visited
    if (mvs[x][y] == 1)
        return true;
    //If the value of the position is not 1 then the position is not visited
    else
        return false;
}//visited

/*
The clear position set the value of the position from 0 to 2
 * 0 : dirt
 * 2 : empty
 * ********************
 * clear Method       *
 * ********************
 */

    public void clear(int x, int y, int h, int wrk[][],int mvs[][]){
         Vac vac = new Vac();
        // Depending on the heading of the agent the action will be performed
        switch(h){
            // If the agent is heading North=0
            case 0 :
                //If the position that the agent in contains dirt
                if (wrk[x][y] == 0 ){
                        //set the value of the position to clean 2
                        wrk[x][y] = 2;
                        System.out.println("Point ("+x+","+y+") Has been cleaned");
                        //detect Obstacles for the next position 
                        vac.detectOb(x, y, h,wrk,mvs);
                }//if
                else{
                    //detect Obstacles for the next position
                    vac.detectOb(x, y, h,wrk,mvs);
                }//else
               
                break;
            // If the agent is heading South=1
            case 1:
                //If the position that the agent in contains dirt
                if (wrk[x][y] == 0 ){
                        //set the value of the position to clean 2
                        wrk[x][y] = 2;
                        System.out.println("Point ("+x+","+y+") Has been cleaned");
                        //detect Obstacles for the next position
                        vac.detectOb(x, y, h,wrk,mvs);
                }//if
                else{
                    //detect Obstacles for the next position
                    vac.detectOb(x, y, h,wrk,mvs);
                }//else
                    
                    break;
            // If the agent is heading East = 2
            case 2 :
                //If the position that the agent in contains dirt
                if (wrk[x][y] == 0 ){
                        //set the value of the position to clean 2
                        wrk[x][y] = 2;
                        System.out.println("Point ("+x+","+y+") Has been cleaned");
                        //detect Obstacles for the next position
                        vac.detectOb(x, y, h,wrk,mvs);
                }//if
                else{
                    //detect Obstacles for the next position
                    vac.detectOb(x, y, h,wrk,mvs);
                }//else
                    
                break;
            case 3:
                // If the agent is heading West = 3
                if (wrk[x][y] == 0 ){
                        //set the value of the position to clean 2
                        wrk[x][y] = 2;
                        System.out.println("Point ("+x+","+y+") Has been cleaned");
                        //detect Obstacles for the next position
                        vac.detectOb(x, y, h,wrk,mvs);
                }//if
                else{
                    //detect Obstacles for the next position
                    vac.detectOb(x, y, h,wrk,mvs);
                }//else
                    
                break;
        }//switch

    }//clear
/*
The stuch method, check weather the agent is stucked or it can moves

 * ********************
 * stuck Method       *
 * ********************
 */
    public boolean stuck(int x, int y,int h,int wrk[][], int mvs[][]){
        boolean temp=false;
    Vac vac = new Vac();
               //If the agent is in the first row ==> Edge
               if (x == 0){
                   //If the agent in the fist column ==> Edge
                    if (y == 0){
                        //If the next column and the same row is an Obstacle = 1 or visited
                        if((wrk[x][y+1] == 1)||(vac.visited(x, y+1, mvs))){
                            //If the next row and the same column is an Obstacle or visited
                            if((wrk[x+1][y] == 1)||(vac.visited(x+1, y, mvs))){
                                //Then the agent is stucked
                                temp = true;
                            }
                        }
                    }
                    else {
                        //If the agent is in the last column ==> Edge
                        if((y == (wrk[x].length -1))){
                            //If the previous column and the same row is an Obstacle or visited
                            if((wrk[x][y-1] == 1)||(vac.visited(x, y-1, mvs))){
                                //If the next row and the same column is an Obstale or visited
                                if((wrk[x+1][y] == 1)||(vac.visited(x+1, y, mvs))){
                                    //Then the agent is stucked
                                    temp = true;
                                }
                            }

                        }
                        else{
                            //If the next column for the same row is visited or Obstacle
                            if((wrk[x][y+1] == 1)||vac.visited(x, y+1, mvs)){
                                //If the previous column for the same row is visited or Obstacle
                                if((wrk[x][y-1] == 1)||vac.visited(x, y-1, mvs)){
                                    //I the next row for the same column is Obstacle or visited
                                    if((wrk[x+1][y] == 1)||vac.visited(x+1, y, mvs)){
                                        // Then the agent is stucked
                                        temp = true;
                                    }
                                }

                            }
                        }
                    }

                }//if
                else{
                    //If the agent in the last row ==> Edge
                    if(x == wrk.length -1){
                        // If the agent is in the first column ==> Edge
                        if (y == 0){
                            //If the next column for the same row is Obstacle or visited
                            if((wrk[x][y+1] == 1)||(vac.visited(x, y+1, mvs))){
                                //If the previous row for the same column is visited or Obstacle
                                if((wrk[x-1][y] == 1)||(vac.visited(x-1, y, mvs))){
                                    //The agent is stucked
                                    temp = true;
                                }
                            }
                        }
                        else {
                            // If he agent is in the last column ==> Edge
                            if((y == (wrk[x].length -1))){
                                //If the previous column for the same row is Obstacle or visited
                                if((wrk[x][y-1] == 1)||(vac.visited(x, y-1, mvs))){
                                    //If the previous row for the same column is visited or Obstacle
                                    if((wrk[x-1][y] == 1)||(vac.visited(x-1, y, mvs))){
                                        //Then the agent is stucked
                                        temp = true;
                                    }
                                }

                            }
                        }

                    }
                    else{
                        // If the agetn is in the first column ==> Edge
                        if (y == 0){
                            //If the next column for the same row is Obstacle or visited
                            if((wrk[x][y+1] == 1)||(vac.visited(x, y+1, mvs))){
                                //If the next row for the same column is Obstacle or visited
                                if((wrk[x+1][y] == 1)||(vac.visited(x+1, y, mvs))){
                                    //If the previous row for the same column is visited or Obstacle
                                    if((wrk[x-1][y] == 1)||(vac.visited(x-1, y, mvs))){
                                        //Then the agent is stucked
                                        temp = true;
                                    }

                                }
                            }
                        }
                        else {
                            // If the agent in the last column ==> Edge
                            if((y == (wrk[x].length -1))){
                                //If the pervious column for the same row is Obstacle or visited
                                if((wrk[x][y-1] == 1)||(vac.visited(x, y-1, mvs))){
                                    //If the next row for the same column is Obstacle or visited
                                    if((wrk[x+1][y] == 1)||(vac.visited(x+1, y, mvs))){
                                        //If the previous row for the same column is Obstacle or visited
                                        if((wrk[x-1][y] == 1)||(vac.visited(x-1, y, mvs))){
                                            //Then agent is stucked
                                            temp = true;
                                        }
                                    }
                                }
                            }
                            else{
                                //If the agent is not in any edges of the array
                                //If the previous column for the same row is Obstacle or visited
                                if((wrk[x][y-1] == 1)||(vac.visited(x, y-1, mvs))){
                                    //If the next row for the same column is Obstacle or visited
                                    if((wrk[x+1][y] == 1)||(vac.visited(x+1, y, mvs))){
                                        //If the previous row for the same column is visited or Obstacle
                                        if((wrk[x-1][y] == 1)||(vac.visited(x-1, y, mvs))){
                                            //If the next column for the same row is visited or Obstacle
                                            if((wrk[x][y+1] == 1)||(vac.visited(x, y+1, mvs))){
                                                //The agent is stucked
                                                temp = true;
                                            }
                                        }
                                    }
                                }

                            }
                        }

                    }
                    }
    return temp;

    }//stuck
/*
The retrievePath, is a future work method, if the agent is stucked this method
will run, to find a suitable path that the agent move through.

 * ********************
 * retrievePath Method*
 * ********************
 */
    public void retrievePath(int x, int y,int h,int wrk[][], int mvs[][]){
        
    }

  /*
   * Some of other future work is to make a method to check if all the areas are
   * visited and cleaned
   * I think this code can handle other shapes of the areas except squares and rectangle, like if the rows are
   * different in the number of columns becuase the functions are designed to handle the various number of column
   *
   */

	}//class 
