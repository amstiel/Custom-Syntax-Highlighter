package com.mallowigi.annotators;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;

public class JSAnnotator extends BaseAnnotator {

  private static final TextAttributesKey JSKEYWORD = ObjectUtils.notNull(TextAttributesKey.find("JS.KEYWORD"),
      DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey THIS_SUPER = TextAttributesKey.createTextAttributesKey("JS.THIS_SUPER", JSKEYWORD);
  public static final TextAttributesKey MODULE = TextAttributesKey.createTextAttributesKey("JS.MODULE_KEYWORD", JSKEYWORD);
  public static final TextAttributesKey DEBUGGER = TextAttributesKey.createTextAttributesKey("JS.DEBUGGER_STMT", JSKEYWORD);
  public static final TextAttributesKey NULL = TextAttributesKey.createTextAttributesKey("JS.NULL_UNDEFINED", JSKEYWORD);
  public static final TextAttributesKey VAL = TextAttributesKey.createTextAttributesKey("JS.VAR_DEF", JSKEYWORD);
  public static final TextAttributesKey FUNCTION = TextAttributesKey.createTextAttributesKey("JS.FUNCTION", JSKEYWORD);
  public static final TextAttributesKey RETURN = TextAttributesKey.createTextAttributesKey("JS.RETURN", JSKEYWORD);
  public static final TextAttributesKey ASYNC = TextAttributesKey.createTextAttributesKey("JS.ASYNC", JSKEYWORD);
  public static final TextAttributesKey AWAIT = TextAttributesKey.createTextAttributesKey("JS.AWAIT", JSKEYWORD);
  public static final TextAttributesKey SWITCH_CASE = TextAttributesKey.createTextAttributesKey("JS.SWITCH_CASE", JSKEYWORD);
  public static final TextAttributesKey IF_ELSE = TextAttributesKey.createTextAttributesKey("JS.IF_ELSE", JSKEYWORD);
  public static final TextAttributesKey TRY_CATCH = TextAttributesKey.createTextAttributesKey("JS.TRY_CATCH", JSKEYWORD);
  public static final TextAttributesKey FOR = TextAttributesKey.createTextAttributesKey("JS.FOR", JSKEYWORD);

  @Override
  protected TextAttributesKey getKeywordKind(@NotNull final PsiElement element) {
    TextAttributesKey kind = null;
    switch (element.getText()) {
      case "this":
      case "super":
        kind = THIS_SUPER;
        break;
      case "export":
      case "import":
      case "require":
      case "from":
      case "module":
      case "as":
      case "default":
        kind = MODULE;
        break;
      case "debugger":
        kind = DEBUGGER;
        break;
      case "null":
      case "undefined":
        kind = NULL;
        break;
      case "var":
      case "let":
      case "const":
        kind = VAL;
        break;
      case "function":
        kind = FUNCTION;
        break;
      case "return":
        kind = RETURN;
        break;
      case "async":
        kind = ASYNC;
        break;
      case "await":
        kind = AWAIT;
        break;
      case "switch":
      case "case":
      case "break":
        kind = SWITCH_CASE;
        break;
      case "if":
      case "else":
        kind = IF_ELSE;
        break;
      case "try":
      case "throw":
        kind = TRY_CATCH;
        break;
      case "for":
        kind = FOR;
        break;
    }
    return kind;
  }
}
