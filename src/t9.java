public class t9 {
    public static void main(String[] args) {

        String[] a = new String[8];
        String[] b = new String[8];
        String[] c = new String[50];
        for (int i = 0; i < 8; i++) {
            a[i] = asciiToString(String.valueOf(65 + i));
        }
        for (int x = 0; x < 8; x++) {
            b[x] = asciiToString(String.valueOf(65 + x));
        }
        for (int x = 0; x < 50; x++) {
            if (x < 25) {
                c[x] = asciiToString(String.valueOf(65 + x));
            } else {
                c[x] = asciiToString(String.valueOf(72 + x));
            }
        }

        String three = "";
        String[][] possPlace = new String[8][];
        for (int x = 0; x < 8; x++) {
            three += a[x].toString();
            for (int y = 0; y < 8; y++) {
                three += b[y].toString();
                for (int z = 0; z < 50; z++) {
                    three += c[z];
                    //threech.add(three);
                    if (isPlacementSequenceValid(three))
                        System.out.print( "\""+ three + "\" , ");
                    three = three.substring(0, 2);
                }
                three = three.substring(0, 1);
            }
            three = "";
        }
    }

    public static String asciiToString(String value) {
        StringBuffer sbu = new StringBuffer();
        String c = value;
        sbu.append((char) Integer.parseInt(c));
        return sbu.toString();
    }
    static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement is well-formed
        // It is empty
        if (placement==null||placement=="")
            return false;
        if (placement.length()%3!=0)
            return false;
        //Loop to show every element is in the range
        for(int i=0;i<placement.length();i++){
            if ((i%3==0||i%3==1) && ((int)placement.charAt(i)<65||(int)placement.charAt(i)>72))
                return false;
            if (i%3==2 && ((int)placement.charAt(i)<65||(int)placement.charAt(i)>89)&&
                    ((int)placement.charAt(i)<97||(int)placement.charAt(i)>121))
                return false;
        }
        //Loop for recognise the same items
        for (int i=0;i<placement.length()/3-1;i++)
            for (int j=i+1;j<placement.length()/3;j++){
                if (placement.charAt(i*3)==placement.charAt(j*3)&&
                        placement.charAt(i*3+1)==placement.charAt(j*3+1)&&
                        placement.charAt(i*3+2)==placement.charAt(j*3+2))
                    return false;
            }
        return true;
    }
    static boolean isPiecePlacementWellFormed(String piecePlacement) {
        // FIXME Task 2: determine whether a piece placement is well-formed
        int pieceCharAscii;
        pieceCharAscii = (int)piecePlacement.charAt(0);
        if (pieceCharAscii<65||pieceCharAscii>72)
            return false;
        pieceCharAscii = (int)piecePlacement.charAt(1);
        if (pieceCharAscii<65||pieceCharAscii>72)
            return false;
        pieceCharAscii = (int)piecePlacement.charAt(2);
        return (pieceCharAscii>=65&&pieceCharAscii<=89)||(pieceCharAscii>=97&&pieceCharAscii<=121);
    }
    private static int [][][] mask = {
            {{-1,1,0,1,-1,1,-1,0,0},{-1,1,-1,0,-1,1,0,1,0},{0,0,-1,1,-1,1,0,1,-1},{0,1,0,1,-1,0,-1,1,-1},
                    {0,-1,1,-1,1,-1,0,0,1},{0,-1,0,0,1,-1,1,-1,1},{1,0,0,-1,1,-1,0,-1,0},{1,-1,1,-1,1,0,0,-1,0}},
            {{0,1,0,0,-1,1,0,1,-1},{0,0,0,1,-1,1,-1,1,0},{-1,1,0,1,-1,0,0,1,0},{0,1,-1,1,-1,1,0,0,0},
                    {0,-1,0,-1,1,0,1,-1,0},{1,-1,0,-1,1,-1,0,0,0},{0,-1,1,0,1,-1,0,-1,0},{0,0,0,-1,1,-1,0,-1,1}},
            {{0,1,0,0,-1,1,-1,1,0},{-1,0,0,1,-1,1,0,1,0},{0,1,-1,1,-1,0,0,1,0},{0,1,0,1,-1,1,0,0,-1},
                    {0,-1,0,-1,1,0,0,-1,1},{0,-1,0,-1,1,-1,1,0,0},{1,-1,0,0,1,-1,0,-1,0},{0,0,1,-1,1,-1,0,-1,0}},
            {{0,1,0,1,-1,0,0,1,-1},{0,1,0,1,-1,1,-1,0,0},{-1,1,0,0,-1,1,0,1,0},{0,0,-1,1,-1,1,0,1,0},
                    {0,-1,0,0,1,-1,1,-1,0},{1,0,0,-1,1,-1,0,-1,0},{0,-1,1,-1,1,0,0,-1,0},{0,-1,0,-1,1,-1,0,0,1}},
            {{0,1,0,1,-1,0,-1,1,0},{-1,1,0,1,-1,1,0,0,0},{0,1,-1,0,-1,1,0,1,0},{0,0,0,1,-1,1,0,1,-1},
                    {0,-1,0,0,1,-1,0,-1,1},{0,0,0,-1,1,-1,1,-1,0},{1,-1,0,-1,1,0,0,-1,0},{0,-1,1,-1,1,-1,0,0,0}},
            {{0,0,-1,0,-1,1,-1,1,0},{-1,0,0,1,-1,0,0,1,-1},{0,1,-1,1,-1,0,-1,0,0},{-1,1,0,0,-1,1,0,0,-1},
                    {1,0,0,-1,1,0,0,-1,1},{0,-1,1,-1,1,0,1,0,0},{1,-1,0,0,1,-1,0,0,1},{0,0,1,0,1,-1,1,-1,0}},
            {{0,1,-1,0,-1,1,-1,1,0},{-1,0,0,1,-1,1,0,1,-1},{0,1,-1,1,-1,0,-1,1,0},{-1,1,0,1,-1,1,0,0,-1},
                    {1,-1,0,-1,1,0,0,-1,1},{0,-1,1,-1,1,-1,1,0,0},{1,-1,0,0,1,-1,0,-1,1},{0,0,1,-1,1,-1,1,-1,0}},
            {{0,1,-1,1,-1,0,0,1,-1},{0,1,0,1,-1,1,-1,0,-1},{-1,1,0,0,-1,1,-1,1,0},{-1,0,-1,1,-1,1,0,1,0},
                    {1,-1,0,0,1,-1,1,-1,0},{1,0,1,-1,1,-1,0,-1,0},{0,-1,1,-1,1,0,0,-1,1},{0,-1,0,-1,1,-1,1,0,1}}
    };
    public static boolean isPlacementSequenceValid(String placement) {
        // FIXME Task 5: determine whether a placement sequence is valid
        // Determine if the placement is placed well
        if (!isPlacementWellFormed(placement))
            return false;
        // location variable record the piece placement on whole board
        int[] location = new int[50];

        for (int i=0;i<=placement.length()/3-1;i++){
            // twice test
            for (int j=i+1;j<placement.length()/3;j++){
                if (placement.charAt(i*3)==placement.charAt(j*3))
                    return false;
            }

            // Copy the single piece placement
            String piecePlacement = String.valueOf(placement.charAt(i*3))+String.valueOf(placement.charAt(i*3+1))+
                    String.valueOf(placement.charAt(i*3+2));

            // Position of the Mask
            int position;
            if ((int)piecePlacement.charAt(2)<91)
                position = (int)piecePlacement.charAt(2)-65;
            else
                position = (int)piecePlacement.charAt(2)-97+25;

            // Recognise if the piece of placement is well
            if (!isPiecePlacementWellFormed(piecePlacement))
                return false;

            //valid piece test
            if (!isValidPlacement(piecePlacement,position))
                return false;
            // Put the piece placement into the board array
            for (int j=0;j<9;j++){
                if (j/3 == 0) {
                    if (position-11+j>=0 && position-11+j>=(Math.floor(position/10)-1)*10 && position-11+j<Math.floor(position/10)*10)
                        location[j + position - 11] = mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][j];
                    else if (position-11+j<0 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][j]!=0)
                        return false;
                    else if (position-11+j<(Math.floor(position/10)-1)*10||position-11+j>=Math.floor(position/10)*10)
                        if (mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][j]!=0)
                            return false;
                }
                else if (j/3 == 1){
                    if (position-1+j-3>=(Math.floor(position/10)*10) && position-1+j-3<(Math.floor(position/10)+1)*10)
                        location[j-3+position-1]= mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][j];
                    else if (mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][j]!=0)
                        return false;
                    else if (position-1+j-3<(Math.floor(position/10))*10||position-1-3+j>=Math.floor(position/10+1)*10)
                        if (mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][j]!=0)
                            return false;
                }
                else{
                    if (position+9+j-6<50 && position+9+j-6>=(Math.floor(position/10)+1)*10 && position+9+j-6<(Math.floor(position/10)+2)*10)
                        location[j-6+position+9] = mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][j];
                    else if (position+9+j-6<0 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][j]!=0)
                        return false;
                    else if (position+9+j-6<(Math.floor(position/10)+1)*10||position+9-6+j>=Math.floor(position/10+2)*10)
                        if (mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][j]!=0)
                            return false;
                }
            }
            if (i == placement.length()/3-1)
                continue;
            // Decide can the new piece can put in the board
            String piecePlacementNew = String.valueOf(placement.charAt((i+1)*3))+String.valueOf(placement.charAt((i+1)*3+1))+
                    String.valueOf(placement.charAt((i+1)*3+2));

            // position of new piece
            int positionNew;
            if ((int)piecePlacementNew.charAt(2)<91)
                positionNew = (int)piecePlacementNew.charAt(2)-65;
            else
                positionNew = (int)piecePlacementNew.charAt(2)-97+25;

            if(!isPiecePlacementWellFormed(piecePlacementNew))
                return false;

            // Recognise the up-lay and the low-lay whether it is same place
            for (int j=0;j<9;j++){
                if (j/3==0){
                    if (positionNew-11+j>=0 && positionNew-11+j>=(Math.floor(positionNew/10)-1)*10 && positionNew-11+j<Math.floor(positionNew/10)*10){
                        if (location[j+positionNew-11]==1 && mask[(int)piecePlacementNew.charAt(0)-65][(int)piecePlacementNew.charAt(1)-65][j]!=0)
                            return false;
                        else if (location[j+positionNew-11]==-1 && mask[(int)piecePlacementNew.charAt(0)-65][(int)piecePlacementNew.charAt(1)-65][j]==-1)
                            return false;
                        if (isOverlap(location,piecePlacementNew,positionNew+j-11,j))
                            return false;
                    }
                    else if (positionNew-11+j<0 && mask[(int)piecePlacementNew.charAt(0)-65][(int)piecePlacementNew.charAt(1)-65][j]!=0)
                        return false;

                }
                else if (j/3==1){
                    if (positionNew-1+j-3>=(Math.floor(positionNew/10)*10) && positionNew-1+j-3<(Math.floor(positionNew/10)+1)*10){
                        if (location[j+positionNew-1-3]==1 && mask[(int)piecePlacementNew.charAt(0)-65][(int)piecePlacementNew.charAt(1)-65][j]!=0)
                            return false;
                        else if (location[j+positionNew-1-3]==-1 && mask[(int)piecePlacementNew.charAt(0)-65][(int)piecePlacementNew.charAt(1)-65][j]==-1)
                            return false;
                        if (isOverlap(location,piecePlacementNew,positionNew+j-1-3,j))
                            return false;
                    }
                    else if (mask[(int)piecePlacementNew.charAt(0)-65][(int)piecePlacementNew.charAt(1)-65][j]!=0)
                        return false;

                }
                else {
                    if (positionNew+9+j-6<50 && positionNew+9+j-6>=(Math.floor(positionNew/10)+1)*10 && positionNew+9+j-6<(Math.floor(positionNew/10)+2)*10){
                        if (location[j+positionNew+9-6]==1 && mask[(int)piecePlacementNew.charAt(0)-65][(int)piecePlacementNew.charAt(1)-65][j]!=0)
                            return false;
                        else if (location[j+positionNew+9-6]==-1 && mask[(int)piecePlacementNew.charAt(0)-65][(int)piecePlacementNew.charAt(1)-65][j]==-1)
                            return false;
                        if (isOverlap(location,piecePlacementNew,positionNew+j+9-6,j))
                            return false;
                    }
                    else if (positionNew+9+j-6>=50 && mask[(int)piecePlacementNew.charAt(0)-65][(int)piecePlacementNew.charAt(1)-65][j]!=0)
                        return false;
                }
            }
        }
        return true;
    }
    static boolean isValidPlacement(String piecePlacement,int position){
        if ((int)piecePlacement.charAt(0)-65!=1 && (int)piecePlacement.charAt(0)-65!=4){
            if (position<10||position>39||position%10==0||position%10==9)
                return false;
        }
        else if ((int)piecePlacement.charAt(0)-65==1){
            if ((int)piecePlacement.charAt(1)-65==0||(int)piecePlacement.charAt(1)-65==6){
                if (position<10||position>39||position%10==9)
                    return false;}
            else if ((int)piecePlacement.charAt(1)-65==1||(int)piecePlacement.charAt(1)-65==7){
                if (position>39||position%10==0||position%10==9)
                    return false;}
            else if ((int)piecePlacement.charAt(1)-65==2||(int)piecePlacement.charAt(1)-65==4){
                if (position<10||position>39||position%10==0)
                    return false; }
            else{
                if (position<10||position%10==0||position%10==9)
                    return false; }
        }
        else{
            if ((int)piecePlacement.charAt(1)-65==0||(int)piecePlacement.charAt(1)-65==6){
                if (position<10||position>39||position%10==0)
                    return false;}
            else if ((int)piecePlacement.charAt(1)-65==1||(int)piecePlacement.charAt(1)-65==7){
                if (position<10||position%10==0||position%10==9)
                    return false;}
            else if ((int)piecePlacement.charAt(1)-65==2||(int)piecePlacement.charAt(1)-65==4){
                if (position<10||position>39||position%10==9)
                    return false; }
            else{
                if (position>39||position%10==0||position%10==9)
                    return false; }
        }
        return true;
    }
    static boolean isOverlap (int[] location, String piecePlacement, int position,int i){
        if (position>=0 && position<10){
            if (location[position+10]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                return true;
            else if (location[position+10]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                return true;
            if (position!=0 && position!=9){
                if (location[position+1]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                    return true;
                else if (location[position+1]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                    return true;
                if (location[position-1]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                    return true;
                else if (location[position-1]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                    return true;
            }
            else if (position==0){
                if (location[position+1]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                    return true;
                else if (location[position+1]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                    return true;
            }
            else {
                if (location[position-1]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                    return true;
                else if (location[position-1]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                    return true;
            }
        }
        else if (position<50 && position>=40){
            if (location[position-10]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                return true;
            else if (location[position-10]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                return true;
            if (position!=40 && position !=49){
                if (location[position+1]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                    return true;
                else if (location[position+1]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                    return true;
                if (location[position-1]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                    return true;
                else if (location[position-1]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                    return true;
            }
            else if (position==40){
                if (location[position+1]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                    return true;
                else if (location[position+1]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                    return true;
            }
            else {
                if (location[position-1]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                    return true;
                else if (location[position-1]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                    return true;
            }
        }
        else if (position%10==0){
            if (location[position+1]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                return true;
            else if (location[position+1]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                return true;
            if (location[position+10]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                return true;
            else if (location[position+10]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                return true;
            if (location[position-10]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                return true;
            else if (location[position-10]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                return true;
        }
        else if (position%10 == 9){
            if (location[position-1]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                return true;
            else if (location[position-1]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                return true;
            if (location[position+10]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                return true;
            else if (location[position+10]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                return true;
            if (location[position-10]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                return true;
            else if (location[position-10]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                return true;
        }
        else {
            if (location[position+10]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                return true;
            else if (location[position+10]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                return true;
            if (location[position-10]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                return true;
            else if (location[position-10]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                return true;
            if (location[position+1]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                return true;
            else if (location[position+1]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                return true;
            if (location[position-1]==1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] !=0)
                return true;
            else if (location[position-1]==-1 && mask[(int)piecePlacement.charAt(0)-65][(int)piecePlacement.charAt(1)-65][i] ==-1)
                return true;
        }

        return false;
    }


}


