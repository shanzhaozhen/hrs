package com.sbgs.hrscommon.utils;

import com.sbgs.hrscommon.dto.TreeModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TreeUtils {

    /**
     * 生成树状结构
     * @param nodeList
     * @return
     */
    public static List<TreeModel> builtTree(List<? extends TreeModel> nodeList) {
        List<TreeModel> rootList = new ArrayList<>();
        List<TreeModel> noRootList = new ArrayList<>();

        for (TreeModel node : nodeList) {
            if (node.getPid() == null || node.getPid() <= 0) {
                rootList.add(node);
            } else {
                noRootList.add(node);
            }
        }

        getNodeChildren(noRootList, nodeList);

        rootList.sort((Comparator.comparing(TreeModel::getPriority, Comparator.nullsLast(Comparator.naturalOrder()))));

        return rootList;
    }

    /**
     * 对子节点进行递归查找
     * @param noRootList
     * @param children
     * @return
     */
    public static List<? extends TreeModel> getNodeChildren(List<? extends TreeModel> noRootList, List<? extends TreeModel> children) {
        for (TreeModel child : children) {
            List<TreeModel> grandsons = new ArrayList<>();
            for (TreeModel noRoot : noRootList) {
                if (child.getId().equals(noRoot.getPid())) {
                    grandsons.add(noRoot);
                }
            }
            if (grandsons.size() > 0) {
                child.setChildren(getNodeChildren(noRootList, grandsons));
            }
        }
        children.sort((Comparator.comparing(TreeModel::getPriority, Comparator.nullsLast(Comparator.naturalOrder()))));

        return children;
    }


}
