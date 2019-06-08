package com.mallowigi.annotators;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;

public class PHPAnnotator extends BaseAnnotator {

  public static final TextAttributesKey PHP_KEYWORD = ObjectUtils.notNull(TextAttributesKey.find("PHP_KEYWORD"),
          DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey MODIFIER = TextAttributesKey.createTextAttributesKey("PHP.MODIFIER", PHP_KEYWORD);
  public static final TextAttributesKey STATIC_FINAL = TextAttributesKey.createTextAttributesKey("PHP.STATIC_FINAL", PHP_KEYWORD);
  public static final TextAttributesKey THIS_SELF = TextAttributesKey.createTextAttributesKey("PHP.THIS_SELF", PHP_KEYWORD);
  public static final TextAttributesKey USE_NAMESPACE = TextAttributesKey.createTextAttributesKey("PHP.USE_NAMESPACE", PHP_KEYWORD);
  public static final TextAttributesKey FUNCTION = TextAttributesKey.createTextAttributesKey("PHP.FUNCTION", PHP_KEYWORD);

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
      case "$this":
      case "self":
        kind = THIS_SELF;
        break;
      case "use":
      case "namespace":
        kind = USE_NAMESPACE;
        break;
      case "function":
        kind = FUNCTION;
        break;
    }
    return kind;
  }
}