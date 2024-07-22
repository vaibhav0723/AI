import java.util.*;

class node{
	int lbm;
	int lbc;
	int rbm;
	int rbc;
	int p,id,pid;
	int boat;
	int h;
	int fin;
	
	node(){
		fin = 0;
		boat =0;
		id =0;
		pid=0;
		p=0;
		lbm=0;
		lbc=0;
		rbm=0;
		rbc=0;
		h=0;
	}
}

class Astar{
	int size;
	int tempid,max;
	node start;
	node temp;
	ArrayList<node> open,close;
	
	Astar(){
		size = 5;
		tempid = 0;
		
		start = new node();
		start.lbm = size;
		start.lbc = size;
		start.rbm = 0;
		start.rbc = 0;
		start.p = 1;
		start.id = 0;
		start.pid = 0;
		start.boat = 1;
		start.h = 1;
		start.fin = 1;
		
		open = new ArrayList<node>();
		close = new ArrayList<node>();
		
		open.add(start);
	}
	
	node heuristic(node n) {
		n.h = n.p * 100 + (n.lbm*n.lbc) + (n.rbm*n.rbc) + n.rbm*10+n.rbc;
		return n;
	}
	
	void remove_same() {
		for(int i=0;i<close.size();i++) {
			int j=0;
			int s = open.size();
			node n1 = close.get(i);
			while(j < s) {
				node n = open.get(j);
				if(n1.lbc == n.lbc && n1.lbm == n.lbm && n1.rbc == n.rbc && n1.rbm == n.rbm && n1.boat == n.boat) {
					System.out.print("\nCLOSE List COMPARED Removed "+n.lbm+n.lbc );
					open.remove(j);
					s = open.size();
				}else {
					j++;
				}
			}
		}
		
	}
	
	int compare(node n) {
		int i=0;
		int s= open.size();
		while(i < s) {
			node n1 = open.get(i);
			if(n1.lbc == n.lbc && n1.lbm == n.lbm && n1.rbc == n.rbm && n1.rbc == n.rbc && n1.boat == n.boat) {
				return 1;
			}
			i++;
		}
		return 0;
	}
	void remove_open() {
		int i=0;
		int s= open.size();
		while(i < s) {
			node n = open.get(i);
			if(n.lbc < 0 || n.lbc > size || n.rbc < 0 || n.rbc > size || n.lbm < 0 || n.rbm < 0 || n.lbm > size || n.rbm > size || (n.lbm > 0 && n.lbc > n.lbm) || (n.rbm > 0 && n.rbc > n.rbm)) {
				System.out.print("\nOPEN LOGIC Removed "+n.lbm+n.lbc );
				open.remove(i);
				s = open.size();
			}else {
				i++;
			}
		}
	}
	
	void solve() {
		while(!open.isEmpty()) {
			max = 0;
			int i=0;
			int pos=0;
			int s = open.size();
			while(i < s) {
				node n = open.get(i);
				if(n.h > max) {
					max = n.h;
					pos =i;
				}
				i++;
			}
			
			node n = open.get(pos);
			
			if(n.rbc == size && n.rbm == size && n.boat == -1 && n.id != 0) {
			System.out.println("\nGOAL Reached: "+n.lbm+" "+n.lbc+"\t"+n.rbm+" "+n.rbc);
				close.add(n);
				break;
			}
			System.out.println("\nIN\n"+n.lbm+" "+n.lbc+"\t"+n.rbm+" "+n.rbc);

			temp=succ(n,1);
			if(compare(temp) == 0)
				open.add(temp);
			temp=succ(n,2);
			if(compare(temp) == 0)
				open.add(temp);
			temp=succ(n,3);
			if(compare(temp) == 0)
				open.add(temp);
			temp=succ(n,4);
			if(compare(temp) == 0)
				open.add(temp);
			temp=succ(n,5);
			if(compare(temp) == 0)
				open.add(temp);
			if(size > 3) {
				temp=succ(n,6);
				if(compare(temp) == 0)
					open.add(temp);
				temp=succ(n,7);
				if(compare(temp) == 0)
					open.add(temp);
				temp=succ(n,8);
				if(compare(temp) == 0)
					open.add(temp);
				temp=succ(n,9);
				if(compare(temp) == 0)
					open.add(temp);
			}
			
			s = open.size();
			 i=0;
			System.out.println("Open1");
			while(i<s) {
				 n = open.get(i);
				System.out.println("\n"+n.lbm+" "+n.lbc+"\t"+n.rbm+" "+n.rbc);
				i++;
			}
			
			n = open.get(pos);
			open.remove(pos);
			close.add(n);
			
			i=0;
			s = close.size();
			System.out.println("Close");
			while(i<s) {
				n = close.get(i);
				System.out.println("\n"+n.lbm+" "+n.lbc+"\t"+n.rbm+" "+n.rbc);
				i++;
			}
			
			remove_open();
			remove_same();
			
			 s = open.size();
			 i=0;
			System.out.println("\nOpen2");
			while(i<s) {
				 n = open.get(i);
				System.out.println("\n"+n.lbm+" "+n.lbc+"\t"+n.rbm+" "+n.rbc);
				i++;
			}
		}
		
	}
	
	void print() {
		int s = close.size();
		int i=0;
		System.out.println("\t Missionaries and Cannibals\n");
		
		node n = close.get(s-1);
		while(n.id != 0) {
			i=0;
			n.fin = 1;
			while(i<s) {
				node n1 = close.get(i);
				if(n1.id == n.pid) {
					n = close.get(i);
					break;
				}
				i++;
			}
		}
		
		i=0;
		s = close.size();
		while(i<s) {
			n = close.get(i);
			if(n.fin == 0) {
				close.remove(i);
				s = close.size();
			}
			else
				i++;
		}
		
		i=0;
		
		while(i<s) {
			n = close.get(i);
			for(int j=0;j<n.lbm;j++) {
				System.out.print("M ");
			}
			
			for(int j=0;j<n.lbc;j++) {
				System.out.print("C ");
			}
			for(int j=0;j<(size-n.lbc);j++) {
				System.out.print("  ");
			}
			for(int j=0;j<(size-n.lbm);j++) {
				System.out.print("  ");
			}
			System.out.print("\t\t");
			for(int j=0;j<n.rbm;j++) {
				System.out.print("M ");
			}
			
			for(int j=0;j<n.rbc;j++) {
				System.out.print("C ");
			}
			for(int j=0;j<(size-n.rbc);j++) {
				System.out.print("  ");
			}
			for(int j=0;j<(size-n.rbm);j++) {
				System.out.print("  ");
			}
			System.out.println("\n");;
			i++;
		}
		
		
		
	/*	
		 s = open.size();
		 i=0;
		System.out.println("Open");
		while(i<s) {
			node n = open.get(i);
			System.out.println("\n"+n.lbm+" "+n.lbc+"\t"+n.rbm+" "+n.rbc);
			i++;
		}*/
	}
	
	void move(node n,node cur) {
		n.lbc = cur.lbc;
		n.lbm = cur.lbm;
		n.rbc = cur.rbc;
		n.rbm = cur.rbm;
		
		n.boat = cur.boat;
		n.h = cur.h;
		
		n.id = cur.id;
		n.p = cur.p;
		n.pid = cur.pid;
		
		
	}
	
	node succ(node cur,int ch) {
		
		node n = new node();
		move(n,cur);
		n.p++;
		n.pid = n.id;
		n.id = tempid++;
		
		if(n.boat == 1) {
			switch(ch) {
			case 1:
				n.lbc--;
				n.rbc++;
				break;
			case 2:
				n.lbm--;
				n.rbm++;
				break;
			case 3:
				n.lbm--;
				n.lbc--;
				n.rbm++;
				n.rbc++;
				break;
			case 4:
				n.lbm--;
				n.lbm--;
				n.rbm++;
				n.rbm++;
				break;
			case 5:
				n.lbc--;
				n.lbc--;
				n.rbc++;
				n.rbc++;
				break;
			case 6:
				n.lbm--;
				n.rbm++;
				n.lbm--;
				n.rbm++;
				n.lbm--;
				n.rbm++;
				break;
			case 7:
				n.lbc--;
				n.rbc++;
				n.lbc--;
				n.rbc++;
				n.lbc--;
				n.rbc++;
				break;
			case 8:
				n.lbc--;
				n.rbc++;
				n.lbc--;
				n.rbc++;
				n.lbm--;
				n.rbm++;
				break;
			case 9:
				n.lbm--;
				n.rbm++;
				n.lbm--;
				n.rbm++;
				n.lbc--;
				n.rbc++;
				break;
			}
		}else if(n.boat == -1) {

			switch(ch) {
			case 1:
				n.lbc++;
				n.rbc--;
				break;
			case 2:
				n.lbm++;
				n.rbm--;
				break;
			case 3:
				n.lbm++;
				n.lbc++;
				n.rbm--;
				n.rbc--;
				break;
			case 4:
				n.lbm++;
				n.lbm++;
				n.rbm--;
				n.rbm--;
				break;
			case 5:
				n.lbc++;
				n.lbc++;
				n.rbc--;
				n.rbc--;
				break;
			case 6:
				n.lbm++;
				n.rbm--;
				n.lbm++;
				n.rbm--;
				n.lbm++;
				n.rbm--;
				break;
			case 7:
				n.lbc++;
				n.rbc--;
				n.lbc++;
				n.rbc--;
				n.lbc++;
				n.rbc--;
				break;
			case 8:
				n.lbc++;
				n.rbc--;
				n.lbc++;
				n.rbc--;
				n.lbm++;
				n.rbm--;
				break;
			case 9:
				n.lbm++;
				n.rbm--;
				n.lbm++;
				n.rbm--;
				n.lbc++;
				n.rbc--;
				break;
			
			}
		}
		n.boat = n.boat*(-1);
		n = heuristic(n);
		
//		System.out.println("\n"+n.lbm+" "+n.lbc+"\t"+n.rbm+" "+n.rbc);

		return n;
	}
	
	
	
}

public class missioanaries_and_cannibals{

	public static void main(String[] args) {

		Astar a = new Astar();
		a.solve();
		a.print();
	}

}