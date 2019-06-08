package com.mallowigi.settings;

import com.intellij.icons.AllIcons;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.psi.codeStyle.DisplayPriority;
import com.intellij.util.ObjectUtils;
import com.intellij.util.PlatformUtils;
import com.mallowigi.annotators.PHPAnnotator;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class PHPColorSettings extends BaseColorSettings {
  @NonNls
  static final AttributesDescriptor[] PHP_ATTRIBUTES;
  @NonNls
  static final Map<String, TextAttributesKey> PHP_DESCRIPTORS = new THashMap<>();

  private static final TextAttributesKey PHPKEYWORD = ObjectUtils.notNull(TextAttributesKey.find("PHP.KEYWORD"),
      DefaultLanguageHighlighterColors.KEYWORD);
  private static final TextAttributesKey VARIABLE = ObjectUtils.notNull(TextAttributesKey.find("PHP.LOCAL_VARIABLE"),
      DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
  private static final TextAttributesKey FUNCTION = PHPAnnotator.FUNCTION;
  private static final TextAttributesKey THIS_SELF = PHPAnnotator.THIS_SELF;
  private static final TextAttributesKey MODIFIER = PHPAnnotator.MODIFIER;
  private static final TextAttributesKey STATIC_FINAL = PHPAnnotator.STATIC_FINAL;
  private static final TextAttributesKey USE_NAMESPACE = PHPAnnotator.USE_NAMESPACE;

  static {
    PHP_ATTRIBUTES = new AttributesDescriptor[]{
        new AttributesDescriptor("Keywords: function", PHPColorSettings.FUNCTION),
        new AttributesDescriptor("Keywords: $this, self", PHPColorSettings.THIS_SELF),
        new AttributesDescriptor("Keywords: private, public, protected", PHPColorSettings.MODIFIER),
        new AttributesDescriptor("Keywords: static, final", PHPColorSettings.STATIC_FINAL),
        new AttributesDescriptor("Keywords: use, namespace", PHPColorSettings.USE_NAMESPACE),
    };

    PHPColorSettings.PHP_DESCRIPTORS.putAll(PHPColorSettings.createAdditionalHlAttrs());
  }

  private static Map<String, TextAttributesKey> createAdditionalHlAttrs() {
    final Map<String, TextAttributesKey> descriptors = new THashMap<>();
    descriptors.put("keyword", PHPColorSettings.PHPKEYWORD);
    descriptors.put("function", PHPColorSettings.FUNCTION);
    descriptors.put("var", PHPColorSettings.VARIABLE);
    descriptors.put("use", PHPColorSettings.USE_NAMESPACE);
    descriptors.put("static", PHPColorSettings.STATIC_FINAL);
    descriptors.put("modifier", PHPColorSettings.MODIFIER);
    descriptors.put("this", PHPColorSettings.THIS_SELF);

    return descriptors;
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return AllIcons.FileTypes.JavaScript;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    final Language lang = ObjectUtils.notNull(Language.findLanguageByID("PHP"), Language.ANY);
    return getSyntaxHighlighterWithFallback(lang);
  }

  @NotNull
  @Override
  public String getDemoText() {
    return
      "<use>namespace</use> Foo\\Bar\\Baz;\n" +
      "\n" +
      "<use>use</use> SomeClass" +
      "\n" +
      "<static>final</static> class MyClass extends MyOtherClass\n" +
      "{\n" +
      "    <modifier>public</modifier> const SINGLE = 0;\n" +
      "    <modifier>private</modifier> <var>$variable</var>;\n" +
      "    <modifier>protected</modifier> <var>$arguments</var>;\n" +
      "}\n" +
      "\n" +
      "<modifier>public</modifier> <function>function</function> getVar()\n" +
      " {\n" +
      "    return <this>$this</this>-><var>$variable</var>;\n" +
      " }";
  }

  @Nullable
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return PHP_DESCRIPTORS;
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return PHP_ATTRIBUTES;
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return "PHP Additions";
  }

  @Override
  public DisplayPriority getPriority() {
    return PlatformUtils.isWebStorm() ? DisplayPriority.KEY_LANGUAGE_SETTINGS : DisplayPriority.LANGUAGE_SETTINGS;
  }
}
