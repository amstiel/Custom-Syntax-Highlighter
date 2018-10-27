package com.mallowigi.annotators;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import org.jetbrains.annotations.NotNull;

public abstract class BaseAnnotator {
  public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {

    if (element instanceof LeafPsiElement) {
      TextRange textRange = element.getTextRange();
      TextRange range = new TextRange(textRange.getStartOffset(), textRange.getEndOffset());
      Annotation annotation = holder.createAnnotation(HighlightSeverity.INFORMATION, range, null);
      TextAttributesKey kind = getKeywordKind(element);

      if (kind != null) {
        annotation.setTextAttributes(kind);
      }
    }
  }

  protected abstract TextAttributesKey getKeywordKind(@NotNull PsiElement element);
}
