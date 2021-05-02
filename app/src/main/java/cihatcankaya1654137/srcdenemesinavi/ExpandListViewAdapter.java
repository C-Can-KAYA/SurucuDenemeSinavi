package cihatcankaya1654137.srcdenemesinavi;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static cihatcankaya1654137.srcdenemesinavi.MainActivity.btnn;
import static cihatcankaya1654137.srcdenemesinavi.MainActivity.dgr;
import static cihatcankaya1654137.srcdenemesinavi.MainActivity.yazi;

public class ExpandListViewAdapter extends BaseExpandableListAdapter{

	public List<String> list_parent;
	public HashMap<String, List<String>> list_child;
	public Context context;
	public TextView txt;
	public TextView txt_child;
	public LayoutInflater inflater;
	public ArrayList<Object> item = new ArrayList<Object>();

	private static DatabaseAccess databaseAccess;//Veri tabanı bağlantısı
	public List<String> rnk=null;//renkleri saklayacak olan liste
	@Override

	public int getGroupCount() {
		
		return list_parent.size();
	}
	
	public ExpandListViewAdapter(Context context, List<String> list_parent, HashMap<String, List<String>> list_child)
	{
		this.context = context;
		this.list_parent = list_parent;
		this.list_child = list_child;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		
		return list_child.get(list_parent.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {

		return list_parent.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {

		return list_child.get(list_parent.get(groupPosition)).get(childPosition);

	}

	@Override
	public long getGroupId(int groupPosition) {

		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {

		return childPosition;
	}

	@Override
	public boolean hasStableIds() {

		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,

			View view, ViewGroup parent) {
		String title_name = (String)getGroup(groupPosition);

		if(view == null)
		{
			inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.listview_header,null);
		}

		txt = (TextView)view.findViewById(R.id.txt_parent);
		txt.setText(title_name);
        txt.setBackgroundColor(Color.parseColor(dgr));
        txt.setTextColor(Color.parseColor(yazi));

		return view;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View view, ViewGroup parent) {


		String txt_child_name = (String)getChild(groupPosition, childPosition);
		if(view==null)
		{
			inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.listview_child, null);
		}
		if(getGroup(groupPosition).toString().equals("Sorular"))
		{
			//view.setBackgroundColor(Color.RED);
		}
		txt_child = (TextView)view.findViewById(R.id.txt_items);
		txt_child.setText(txt_child_name);

		txt_child.setTextColor(Color.parseColor(yazi));
		return view;
	}
		@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {

		return true;
	}
}
