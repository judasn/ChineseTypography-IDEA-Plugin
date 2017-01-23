package com.youmeek.typography;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.youmeek.typography.utils.RegexExpressionUtils;
import com.youmeek.typography.utils.TransitionUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Judas.n
 * 2016-12-31 21:51:09
 * http://www.YouMeek.com
 * admin@youmeek.com
 * 微信公众号：iYouMeek
 * 自己常在 IntelliJ IDEA 中写博文，对于中英文之间的排版，觉得有一个空格后可读性会更加强，顾开发此插件。
 * 本插件的核心转换类是来自 pangu，自己做了小修改。在此表示感谢：https://github.com/vinta/pangu.java
 */
public class TransitionAction extends AnAction {

	@Override
	public void actionPerformed(AnActionEvent e) {
		Editor editor = e.getData(PlatformDataKeys.EDITOR);
		if (null == editor) {
			return;
		}
		SelectionModel selectionModel = editor.getSelectionModel();
		String selectedText = selectionModel.getSelectedText();
		if (StringUtils.isBlank(selectedText)) {
			return;
		}

		//下面这句话也可以使用 lambda 语法写：Runnable runnable = () -> editor.getDocument().replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(), TransitionUtils.spacingText(selectedText));
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				//先对空格、制表符进行换掉，然后再重新进行分隔处理。其中分行不处理，因为在写文章的时候复制的一些内容分行是有意义的。
				editor.getDocument().replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(), TransitionUtils.spacingText(RegexExpressionUtils.replace(selectedText, "\\f|\\r|\\t", "")));
			}
		};

		WriteCommandAction.runWriteCommandAction(e.getData(PlatformDataKeys.PROJECT), runnable);
	}
}
