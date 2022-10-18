package Round1;
public class FastRead{

    public static void main(String[] args) {
        System.out.println(fastRead(700,900,"{\"timestamp\":0,\"book\":\"AAPL\",\"bids\":[{\"price\":800,\"volume\":10}],\"asks\":[{\"price\":3000,\"volume\":10},{\"price\":600,\"volume\":10}]}"));
    }

    public static String fastRead(double maxBuyPrice, double minSellPrice, String marketState){
        if ((0<maxBuyPrice) && (maxBuyPrice<minSellPrice) && (minSellPrice<=1000) && (marketState.length()<=1000)){
            char[] chars = marketState.toCharArray();
            int index = 13;
            int numofChars = 0;
            String actions = "S13 ";
            while(!String.valueOf(chars[index]).equals("\"")){ // reading timestamp
                //we don't care about timestamp
                numofChars++;
                index++;
            }
            
            actions = actions+"R"+Integer.toString(numofChars)+" ";
            actions = actions+"S8 ";
            index+=8;

            numofChars = 0;
            while(!String.valueOf(chars[index]).equals(",")){ // reading book
                // we don't care about book
                numofChars++;
                index++;
            }
            actions = actions+"R"+Integer.toString(numofChars)+" ";
            

            numofChars = 0;
            if (String.valueOf(chars[index]).equals(",")){ // read bids
                actions = actions+"S9 "; // skips ,"bids":[
                index+=9;
                boolean loopOnce = false;
                boolean complete = false;
                while (!complete){
                    if (String.valueOf(chars[index]).equals("]")){
                        actions = actions+"R"+Integer.toString(1)+" ";
                        index++;
                        complete = true;
                        continue;
                    }
                    if (loopOnce) {
                        actions = actions+"S9 "; //skip {"price":
                        index+=9;
                    }else{
                        actions = actions+"R1 "; //start of bid json
                        index++;
                        actions = actions+"S8 "; //skip "price":
                        index+=8;
                    }
                    
                    String price = "";
                    while (!String.valueOf(chars[index]).equals(",")){ // read price value
                        price = price + chars[index];
                        
                        numofChars++;
                        index++;
                    }
                    numofChars++; // compensate for comma
                    index++;
                    actions = actions+"R"+Integer.toString(numofChars)+" ";
                    numofChars = 0;
                    if (Integer.parseInt(price)>=minSellPrice){
                        return actions;
                    }
                    actions = actions+"S9 "; //skip "volume":
                    index+=9;
                    while (!String.valueOf(chars[index]).equals("}")){ // read volume value
                        // we don't care about volume value
                        
                        numofChars++;
                        index++;
                    }
                    

                    if (String.valueOf(chars[index+1]).equals("]")){
                        complete = true;
                        
                    }
                    numofChars+=2;
                    index+=2;
                    
                    actions = actions+"R"+Integer.toString(numofChars)+" ";
                    loopOnce = true;
                    numofChars = 0;
                }
            }



            numofChars = 0;
            if (String.valueOf(chars[index]).equals(",")){ // read asks
                actions = actions+"S9 "; // skip ,"asks":[
                index+=9;
                boolean loopOnce = false;
                boolean complete = false;
                while (!complete){
                    if (String.valueOf(chars[index]).equals("]")){
                        actions = actions+"R"+Integer.toString(1)+" ";
                        complete = true;
                        continue;
                    }
                    if (loopOnce) {
                        actions = actions+"S9 "; //skip {"price":
                        index+=9;
                    }else{
                        actions = actions+"R1 "; //start of bid json
                        index++;
                        actions = actions+"S8 "; //skip "price":
                        index+=8;
                    }
                    
                    String price = "";
                    while (!String.valueOf(chars[index]).equals(",")){ // read price value
                        price = price + chars[index];
                        numofChars++;
                        index++;
                    }
                    numofChars++; // compensate for comma
                    index++;
                    actions = actions+"R"+Integer.toString(numofChars)+" ";
                    numofChars = 0;
                    if (Integer.parseInt(price)<=maxBuyPrice){
                        return actions;
                    }
                    actions = actions+"S9 "; //skip "volume":
                    index+=9;
                    while (!String.valueOf(chars[index]).equals("}")){ // read volume value
                        // we don't care about volume value
                        
                        numofChars++;
                        index++;
                    }
                    

                    if (String.valueOf(chars[index+1]).equals("]")){
                        complete = true;
                        
                    }
                    numofChars+=2;
                    index+=2;
                    
                    actions = actions+"R"+Integer.toString(numofChars)+" ";
                    loopOnce = true;
                    numofChars = 0;
                }
            }




            return actions;




        }else {
            return "";
        }

        
    }

}