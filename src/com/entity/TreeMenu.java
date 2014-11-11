package com.entity;

import java.util.ArrayList;
import java.util.List;


/**
 * DWZ中左边树形结构菜单模型
 * 
 * @author AcoreHeng
 * 
 */
public class TreeMenu {
	private int treeMenuId;
	private String name;// 菜单名
	private String target = "navTab";// navTab或dialog
	private String rel;// navTabId
	private int reloadFlag = 1;// 是否自动刷新(1:自动刷新,0:手动刷新)
	private String href;// 跳转路径
	private int rank;// 排序
	private TreeMenu parent;// 父亲菜单
	private NavMenu navMenu = new NavMenu();// 所在的导航菜单
	private List<TreeMenu> children = new ArrayList<TreeMenu>();// 子菜单们

	public int getTreeMenuId() {
		return treeMenuId;
	}

	public void setTreeMenuId(int treeMenuId) {
		this.treeMenuId = treeMenuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeMenu getParent() {
		return parent;
	}

	public void setParent(TreeMenu parent) {
		this.parent = parent;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public int getReloadFlag() {
		return reloadFlag;
	}

	public void setReloadFlag(int reloadFlag) {
		this.reloadFlag = reloadFlag;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public NavMenu getNavMenu() {
		return navMenu;
	}

	public void setNavMenu(NavMenu navMenu) {
		this.navMenu = navMenu;
	}

	public List<TreeMenu> getChildren() {
		return children;
	}

	public void setChildren(List<TreeMenu> children) {
		this.children = children;
	}

	public String toString() {
		int pid = parent == null ? 0 : parent.treeMenuId;
		return "{\"treeMenuId\":\"" + treeMenuId + "\",\"navMenuId\":\""
				+ navMenu.getNavMenuId() + "\",\"pid\":\"" + pid
				+ "\",\"name\":\"" + name + "\",\"href\":\"" + href + "\"}";
	}
}
