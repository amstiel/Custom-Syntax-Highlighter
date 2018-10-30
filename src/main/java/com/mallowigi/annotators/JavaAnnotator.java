package com.mallowigi.annotators;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;

public class JavaAnnotator extends BaseAnnotator {

  public static final TextAttributesKey JAVA_KEYWORD = ObjectUtils.notNull(TextAttributesKey.find("JAVA_KEYWORD"),
      DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey MODIFIER = TextAttributesKey.createTextAttributesKey("JAVA.MODIFIER", JAVA_KEYWORD);
  public static final TextAttributesKey STATIC_FINAL = TextAttributesKey.createTextAttributesKey("JAVA.STATIC_FINAL", JAVA_KEYWORD);
  public static final TextAttributesKey THIS_SUPER = TextAttributesKey.createTextAttributesKey("JAVA.THIS_SUPER", JAVA_KEYWORD);

  @Override
  protected TextAttributesKey getKeywordKind(@NotNull final PsiElement element) {
    TextAttributesKey kind = null;
    switch (element.getText()) {
      case "private":
      case "public":
      case "protected":
        kind = MODIFIER;
        break;
      case "static":
      case "final":
        kind = STATIC_FINAL;
        break;
      case "this":
      case "super":
        kind = THIS_SUPER;
        break;
    }
    return kind;
  }
}
