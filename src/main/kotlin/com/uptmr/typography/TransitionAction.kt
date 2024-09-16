package com.uptmr.typography

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.uptmr.typography.utils.RegexExpressionUtils
import ws.vinta.pangu.Pangu


class TransitionAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getData(PlatformDataKeys.EDITOR) ?: return
        val selectionModel = editor.selectionModel
        val selectedText = selectionModel.selectedText ?: return

        val runnable = Runnable {
            //先对空格、制表符进行换掉，然后再重新进行分隔处理。其中分行不处理，因为在写文章的时候复制的一些内容分行是有意义的。
            editor.document.replaceString(
                selectionModel.selectionStart,
                selectionModel.selectionEnd,
                Pangu().spacingText(
                    RegexExpressionUtils.replace(selectedText, "\\f|\\r|\\t", "")
                )
            )
        }

        WriteCommandAction.runWriteCommandAction(e.getData(PlatformDataKeys.PROJECT), runnable)
    }
}