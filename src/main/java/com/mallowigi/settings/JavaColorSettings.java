package com.mallowigi.settings;

import com.intellij.icons.AllIcons;
import com.intellij.ide.highlighter.JavaHighlightingColors;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.psi.codeStyle.DisplayPriority;
import com.intellij.util.ObjectUtils;
import com.intellij.util.PlatformUtils;
import com.mallowigi.annotators.JavaAnnotator;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class JavaColorSettings extends BaseColorSettings {
  @NonNls
  static final AttributesDescriptor[] JAVA_ATTRIBUTES;
  @NonNls
  static final Map<String, TextAttributesKey> JAVA_DESCRIPTORS = new THashMap<>();

  private static final TextAttributesKey JAVA_KEYWORD = JavaAnnotator.JAVA_KEYWORD;
  private static final TextAttributesKey THIS_SUPER = JavaAnnotator.THIS_SUPER;
  private static final TextAttributesKey MODIFIER = JavaAnnotator.MODIFIER;
  private static final TextAttributesKey STATIC_FINAL = JavaAnnotator.STATIC_FINAL;

  static {
    JAVA_ATTRIBUTES = new AttributesDescriptor[]{
        new AttributesDescriptor("Keywords: this, super", JavaColorSettings.THIS_SUPER),
        new AttributesDescriptor("Keywords: private, public, protected", JavaColorSettings.MODIFIER),
        new AttributesDescriptor("Keywords: static, final", JavaColorSettings.STATIC_FINAL),
    };

    JavaColorSettings.JAVA_DESCRIPTORS.putAll(JavaColorSettings.createAdditionalHlAttrs());
  }

  private static Map<String, TextAttributesKey> createAdditionalHlAttrs() {
    final Map<String, TextAttributesKey> descriptors = new THashMap<>();

    descriptors.put("field", JavaHighlightingColors.INSTANCE_FIELD_ATTRIBUTES);
    descriptors.put("unusedField", CodeInsightColors.NOT_USED_ELEMENT_ATTRIBUTES);
    descriptors.put("error", CodeInsightColors.ERRORS_ATTRIBUTES);
    descriptors.put("warning", CodeInsightColors.WARNINGS_ATTRIBUTES);
    descriptors.put("weak_warning", CodeInsightColors.WEAK_WARNING_ATTRIBUTES);
    descriptors.put("server_problems", CodeInsightColors.GENERIC_SERVER_ERROR_OR_WARNING);
    descriptors.put("server_duplicate", CodeInsightColors.DUPLICATE_FROM_SERVER);
    descriptors.put("unknownType", CodeInsightColors.WRONG_REFERENCES_ATTRIBUTES);
    descriptors.put("localVar", JavaHighlightingColors.LOCAL_VARIABLE_ATTRIBUTES);
    descriptors.put("reassignedLocalVar", JavaHighlightingColors.REASSIGNED_LOCAL_VARIABLE_ATTRIBUTES);
    descriptors.put("reassignedParameter", JavaHighlightingColors.REASSIGNED_PARAMETER_ATTRIBUTES);
    descriptors.put("implicitAnonymousParameter", JavaHighlightingColors.IMPLICIT_ANONYMOUS_CLASS_PARAMETER_ATTRIBUTES);
    descriptors.put("static", JavaHighlightingColors.STATIC_FIELD_ATTRIBUTES);
    descriptors.put("static_final", JavaHighlightingColors.STATIC_FINAL_FIELD_ATTRIBUTES);
    descriptors.put("deprecated", CodeInsightColors.DEPRECATED_ATTRIBUTES);
    descriptors.put("for_removal", CodeInsightColors.MARKED_FOR_REMOVAL_ATTRIBUTES);
    descriptors.put("constructorCall", JavaHighlightingColors.CONSTRUCTOR_CALL_ATTRIBUTES);
    descriptors.put("constructorDeclaration", JavaHighlightingColors.CONSTRUCTOR_DECLARATION_ATTRIBUTES);
    descriptors.put("methodCall", JavaHighlightingColors.METHOD_CALL_ATTRIBUTES);
    descriptors.put("methodDeclaration", JavaHighlightingColors.METHOD_DECLARATION_ATTRIBUTES);
    descriptors.put("static_method", JavaHighlightingColors.STATIC_METHOD_ATTRIBUTES);
    descriptors.put("abstract_method", JavaHighlightingColors.ABSTRACT_METHOD_ATTRIBUTES);
    descriptors.put("inherited_method", JavaHighlightingColors.INHERITED_METHOD_ATTRIBUTES);
    descriptors.put("param", JavaHighlightingColors.PARAMETER_ATTRIBUTES);
    descriptors.put("lambda_param", JavaHighlightingColors.LAMBDA_PARAMETER_ATTRIBUTES);
    descriptors.put("class", JavaHighlightingColors.CLASS_NAME_ATTRIBUTES);
    descriptors.put("anonymousClass", JavaHighlightingColors.ANONYMOUS_CLASS_NAME_ATTRIBUTES);
    descriptors.put("typeParameter", JavaHighlightingColors.TYPE_PARAMETER_NAME_ATTRIBUTES);
    descriptors.put("abstractClass", JavaHighlightingColors.ABSTRACT_CLASS_NAME_ATTRIBUTES);
    descriptors.put("interface", JavaHighlightingColors.INTERFACE_NAME_ATTRIBUTES);
    descriptors.put("enum", JavaHighlightingColors.ENUM_NAME_ATTRIBUTES);
    descriptors.put("annotationName", JavaHighlightingColors.ANNOTATION_NAME_ATTRIBUTES);
    descriptors.put("annotationAttributeName", JavaHighlightingColors.ANNOTATION_ATTRIBUTE_NAME_ATTRIBUTES);
    descriptors.put("javadocTagValue", JavaHighlightingColors.DOC_COMMENT_TAG_VALUE);
    descriptors.put("instanceFinalField", JavaHighlightingColors.INSTANCE_FINAL_FIELD_ATTRIBUTES);
    descriptors.put("staticallyConstImported", JavaHighlightingColors.STATIC_FINAL_FIELD_IMPORTED_ATTRIBUTES);
    descriptors.put("staticallyImported", JavaHighlightingColors.STATIC_FIELD_IMPORTED_ATTRIBUTES);
    descriptors.put("static_imported_method", JavaHighlightingColors.STATIC_METHOD_CALL_IMPORTED_ATTRIBUTES);

    descriptors.put("keyword", JavaColorSettings.JAVA_KEYWORD);
    descriptors.put("this", JavaColorSettings.THIS_SUPER);
    descriptors.put("sf", JavaColorSettings.STATIC_FINAL);
    descriptors.put("modifier", JavaColorSettings.MODIFIER);

    return descriptors;
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return AllIcons.FileTypes.Java;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    final Language lang = ObjectUtils.notNull(Language.findLanguageByID("JAVA"), Language.ANY);
    return getSyntaxHighlighterWithFallback(lang);
  }

  @NotNull
  @Override
  public String getDemoText() {
    return
        "public class <class>SomeClass</class> extends <class>BaseClass</class> {\n" +
            "  <modifier>private</modifier> <sf>static final</sf> <field>field</field> = null;\n" +
            "  <modifier>protected</modifier> <sf>final</sf> <field>otherField</field>;\n\n" +
            "  <modifier>public</modifier> <constructorDeclaration>SomeClass</constructorDeclaration>(<interface>AnInterface</interface> " +
            "<param>param1</param>, int[] <reassignedParameter>reassignedParam</reassignedParameter>,\n" +
            "                  int <param>param2</param>\n" +
            "                  int <param>param3</param>) {\n" +
            "    <this>super</this>(<param>param1</param>);\n" +
            "    <this>this</this>.<warning>field</warning> = <param>param1</param>;\n" +
            "  }\n " +
            "}\n";
  }

  @Nullable
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return JAVA_DESCRIPTORS;
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return JAVA_ATTRIBUTES;
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return "Java Additions";
  }

  @Override
  public DisplayPriority getPriority() {
    return PlatformUtils.isIntelliJ() ? DisplayPriority.KEY_LANGUAGE_SETTINGS : DisplayPriority.LANGUAGE_SETTINGS;
  }
}
