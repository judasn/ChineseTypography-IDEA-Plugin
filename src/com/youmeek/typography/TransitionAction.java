package com.youmeek.typography;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.youmeek.typography.utils.TransitionUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Judas.n
 * 2016-12-31 21:51:09
 * http://www.YouMeek.com
 * admin@youmeek.com
 * 微信公众号：iYouMeek
 * 我常用 IntelliJ IDEA 进行写作，开发此插件可以快速对文章内容的中文、英文、符号之间增加空格，增加文章可读性。
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
				editor.getDocument().replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(), TransitionUtils.spacingText(selectedText));
			}
		};

		WriteCommandAction.runWriteCommandAction(e.getData(PlatformDataKeys.PROJECT), runnable);
	}
}
