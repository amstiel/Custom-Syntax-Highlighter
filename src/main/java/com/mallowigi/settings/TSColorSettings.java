package com.mallowigi.settings;

import com.intellij.icons.AllIcons;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.psi.codeStyle.DisplayPriority;
import com.intellij.util.ObjectUtils;
import com.intellij.util.PlatformUtils;
import com.mallowigi.annotators.TSAnnotator;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class TSColorSettings extends BaseColorSettings {
  @NonNls
  private static final AttributesDescriptor[] TS_ATTRIBUTES;
  @NonNls
  private static final Map<String, TextAttributesKey> TS_DESCRIPTORS = new THashMap<>();

  private static final TextAttributesKey PRIVATE = ObjectUtils.notNull(TSAnnotator.PRIVATE);

  static {
    TS_ATTRIBUTES = new AttributesDescriptor[]{
        new AttributesDescriptor("Keywords: private, public, protected", PRIVATE),
    };

    TS_DESCRIPTORS.putAll(createAdditionalHlAttrs());
    TS_DESCRIPTORS.putAll(JSColorSettings.JS_DESCRIPTORS);
  }

  private static Map<String, TextAttributesKey> createAdditionalHlAttrs() {
    final Map<String, TextAttributesKey> descriptors = new THashMap<>();
    descriptors.put("private", PRIVATE);

    return descriptors;
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return AllIcons.FileTypes.TypeScript;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    final Language lang = ObjectUtils.notNull(Language.findLanguageByID("TypeScript"), Language.ANY);
    return getSyntaxHighlighterWithFallback(lang);
  }

  @NotNull
  @Override
  public String getDemoText() {
    return "<import>import</import> {_} <import>from</import> 'lodash';\n\n" +
        "<keyword>class</keyword> <class>MyType</class> <keyword>extends</keyword> <class>AbstractClass</class> {\n" +
        "  <private>private</private> <local_variable>field</local_variable>: <class>String</class>;\n" +
        "  <private>protected</private> <local_variable>protect</local_variable>: <class>Number</class>;\n" +
        "  <private>public</private> <local_variable>num</local_variable> = 10;\n" +
        "}";
  }

  @Nullable
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return TS_DESCRIPTORS;
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return TS_ATTRIBUTES;
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return "TypeScript Additions";
  }

  @Override
  public DisplayPriority getPriority() {
    return PlatformUtils.isWebStorm() ? DisplayPriority.KEY_LANGUAGE_SETTINGS : DisplayPriority.LANGUAGE_SETTINGS;
  }
}
