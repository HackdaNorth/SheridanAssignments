import java.util.Arrays;

class smallestNum { 
    public static void main(String[] args) 
    { 
        int[] num = {25,37,29};
	    for(int i=0; i<num.length;i++){
	        if(num[0] > num[1]) { //23<27
	            if(num[1] < num[2]) {//27<29
	                System.out.println("Number 1 Is the winner!");
	                   break;
	            } else { // 27>29
	                System.out.println("Number 2 Is the winner!");
	                break;
	            }
	        } else { //23>27
	                System.out.println("Number 0 Is the winner!");
	                   break;
	        }
	    }
    } 
} 