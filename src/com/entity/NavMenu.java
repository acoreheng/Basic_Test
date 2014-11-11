package com.entity;

import java.util.ArrayList;
import java.util.List;


/**
 * DWZ中导航菜单模型
 * 
 * @author AcoreHeng
 * 
 */

public class NavMenu {
	private int navMenuId;
	private String name;
	private String href;
	private int rank;
	private List<TreeMenu> treeMenus = new ArrayList<TreeMenu>();

	public int getNavMenuId() {
		return navMenuId;
	}

	public void setNavMenuId(int navMenuId) {
		this.navMenuId = navMenuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreeMenu> getTreeMenus() {
		return treeMenus;
	}

	public void setTreeMenus(List<TreeMenu> treeMenus) {
		this.treeMenus = treeMenus;
	}

	public String getHref() {
		if (href != null) {
			if (href.contains("{id}")) {
				href = href.replace("{id}", String.valueOf(navMenuId));
			}
		}
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}
