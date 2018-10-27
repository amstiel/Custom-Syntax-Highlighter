package com.mallowigi.annotators;

import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;

public class TSAnnotator extends JSAnnotator implements Annotator {
  private static final TextAttributesKey JSKEYWORD = ObjectUtils.notNull(TextAttributesKey.find("JS.KEYWORD"),
      DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey PRIVATE = TextAttributesKey.createTextAttributesKey("TS.PRIVATE_PUBLIC", JSKEYWORD);

  @Override
  public TextAttributesKey getKeywordKind(@NotNull final PsiElement element) {
    TextAttributesKey kind = super.getKeywordKind(element);
    if (kind == null) {
      switch (element.getText()) {
        case "public":
        case "protected":
        case "private":
          kind = PRIVATE;
          break;
      }
      return kind;
    }
    return kind;
  }
}
