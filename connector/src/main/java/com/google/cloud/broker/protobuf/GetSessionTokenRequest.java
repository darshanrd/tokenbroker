// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: broker.proto

package com.google.cloud.broker.protobuf;

/**
 * Protobuf type {@code com.google.cloud.broker.protobuf.GetSessionTokenRequest}
 */
public  final class GetSessionTokenRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.google.cloud.broker.protobuf.GetSessionTokenRequest)
    GetSessionTokenRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetSessionTokenRequest.newBuilder() to construct.
  private GetSessionTokenRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetSessionTokenRequest() {
    scope_ = "";
    owner_ = "";
    target_ = "";
    renewer_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetSessionTokenRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            scope_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            owner_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            target_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            renewer_ = s;
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.google.cloud.broker.protobuf.BrokerProto.internal_static_com_google_cloud_broker_protobuf_GetSessionTokenRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.cloud.broker.protobuf.BrokerProto.internal_static_com_google_cloud_broker_protobuf_GetSessionTokenRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.cloud.broker.protobuf.GetSessionTokenRequest.class, com.google.cloud.broker.protobuf.GetSessionTokenRequest.Builder.class);
  }

  public static final int SCOPE_FIELD_NUMBER = 1;
  private volatile java.lang.Object scope_;
  /**
   * <code>string scope = 1;</code>
   */
  public java.lang.String getScope() {
    java.lang.Object ref = scope_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      scope_ = s;
      return s;
    }
  }
  /**
   * <code>string scope = 1;</code>
   */
  public com.google.protobuf.ByteString
      getScopeBytes() {
    java.lang.Object ref = scope_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      scope_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int OWNER_FIELD_NUMBER = 2;
  private volatile java.lang.Object owner_;
  /**
   * <code>string owner = 2;</code>
   */
  public java.lang.String getOwner() {
    java.lang.Object ref = owner_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      owner_ = s;
      return s;
    }
  }
  /**
   * <code>string owner = 2;</code>
   */
  public com.google.protobuf.ByteString
      getOwnerBytes() {
    java.lang.Object ref = owner_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      owner_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TARGET_FIELD_NUMBER = 3;
  private volatile java.lang.Object target_;
  /**
   * <code>string target = 3;</code>
   */
  public java.lang.String getTarget() {
    java.lang.Object ref = target_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      target_ = s;
      return s;
    }
  }
  /**
   * <code>string target = 3;</code>
   */
  public com.google.protobuf.ByteString
      getTargetBytes() {
    java.lang.Object ref = target_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      target_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int RENEWER_FIELD_NUMBER = 4;
  private volatile java.lang.Object renewer_;
  /**
   * <code>string renewer = 4;</code>
   */
  public java.lang.String getRenewer() {
    java.lang.Object ref = renewer_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      renewer_ = s;
      return s;
    }
  }
  /**
   * <code>string renewer = 4;</code>
   */
  public com.google.protobuf.ByteString
      getRenewerBytes() {
    java.lang.Object ref = renewer_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      renewer_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getScopeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, scope_);
    }
    if (!getOwnerBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, owner_);
    }
    if (!getTargetBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, target_);
    }
    if (!getRenewerBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, renewer_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getScopeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, scope_);
    }
    if (!getOwnerBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, owner_);
    }
    if (!getTargetBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, target_);
    }
    if (!getRenewerBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, renewer_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.google.cloud.broker.protobuf.GetSessionTokenRequest)) {
      return super.equals(obj);
    }
    com.google.cloud.broker.protobuf.GetSessionTokenRequest other = (com.google.cloud.broker.protobuf.GetSessionTokenRequest) obj;

    boolean result = true;
    result = result && getScope()
        .equals(other.getScope());
    result = result && getOwner()
        .equals(other.getOwner());
    result = result && getTarget()
        .equals(other.getTarget());
    result = result && getRenewer()
        .equals(other.getRenewer());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + SCOPE_FIELD_NUMBER;
    hash = (53 * hash) + getScope().hashCode();
    hash = (37 * hash) + OWNER_FIELD_NUMBER;
    hash = (53 * hash) + getOwner().hashCode();
    hash = (37 * hash) + TARGET_FIELD_NUMBER;
    hash = (53 * hash) + getTarget().hashCode();
    hash = (37 * hash) + RENEWER_FIELD_NUMBER;
    hash = (53 * hash) + getRenewer().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.cloud.broker.protobuf.GetSessionTokenRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.cloud.broker.protobuf.GetSessionTokenRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.cloud.broker.protobuf.GetSessionTokenRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.cloud.broker.protobuf.GetSessionTokenRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.cloud.broker.protobuf.GetSessionTokenRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.cloud.broker.protobuf.GetSessionTokenRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.cloud.broker.protobuf.GetSessionTokenRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.cloud.broker.protobuf.GetSessionTokenRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.cloud.broker.protobuf.GetSessionTokenRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.google.cloud.broker.protobuf.GetSessionTokenRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.cloud.broker.protobuf.GetSessionTokenRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.cloud.broker.protobuf.GetSessionTokenRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.google.cloud.broker.protobuf.GetSessionTokenRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.google.cloud.broker.protobuf.GetSessionTokenRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.google.cloud.broker.protobuf.GetSessionTokenRequest)
      com.google.cloud.broker.protobuf.GetSessionTokenRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.cloud.broker.protobuf.BrokerProto.internal_static_com_google_cloud_broker_protobuf_GetSessionTokenRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.cloud.broker.protobuf.BrokerProto.internal_static_com_google_cloud_broker_protobuf_GetSessionTokenRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.cloud.broker.protobuf.GetSessionTokenRequest.class, com.google.cloud.broker.protobuf.GetSessionTokenRequest.Builder.class);
    }

    // Construct using com.google.cloud.broker.protobuf.GetSessionTokenRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      scope_ = "";

      owner_ = "";

      target_ = "";

      renewer_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.cloud.broker.protobuf.BrokerProto.internal_static_com_google_cloud_broker_protobuf_GetSessionTokenRequest_descriptor;
    }

    @java.lang.Override
    public com.google.cloud.broker.protobuf.GetSessionTokenRequest getDefaultInstanceForType() {
      return com.google.cloud.broker.protobuf.GetSessionTokenRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.google.cloud.broker.protobuf.GetSessionTokenRequest build() {
      com.google.cloud.broker.protobuf.GetSessionTokenRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.google.cloud.broker.protobuf.GetSessionTokenRequest buildPartial() {
      com.google.cloud.broker.protobuf.GetSessionTokenRequest result = new com.google.cloud.broker.protobuf.GetSessionTokenRequest(this);
      result.scope_ = scope_;
      result.owner_ = owner_;
      result.target_ = target_;
      result.renewer_ = renewer_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.google.cloud.broker.protobuf.GetSessionTokenRequest) {
        return mergeFrom((com.google.cloud.broker.protobuf.GetSessionTokenRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.cloud.broker.protobuf.GetSessionTokenRequest other) {
      if (other == com.google.cloud.broker.protobuf.GetSessionTokenRequest.getDefaultInstance()) return this;
      if (!other.getScope().isEmpty()) {
        scope_ = other.scope_;
        onChanged();
      }
      if (!other.getOwner().isEmpty()) {
        owner_ = other.owner_;
        onChanged();
      }
      if (!other.getTarget().isEmpty()) {
        target_ = other.target_;
        onChanged();
      }
      if (!other.getRenewer().isEmpty()) {
        renewer_ = other.renewer_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.google.cloud.broker.protobuf.GetSessionTokenRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.cloud.broker.protobuf.GetSessionTokenRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object scope_ = "";
    /**
     * <code>string scope = 1;</code>
     */
    public java.lang.String getScope() {
      java.lang.Object ref = scope_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        scope_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string scope = 1;</code>
     */
    public com.google.protobuf.ByteString
        getScopeBytes() {
      java.lang.Object ref = scope_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        scope_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string scope = 1;</code>
     */
    public Builder setScope(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      scope_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string scope = 1;</code>
     */
    public Builder clearScope() {
      
      scope_ = getDefaultInstance().getScope();
      onChanged();
      return this;
    }
    /**
     * <code>string scope = 1;</code>
     */
    public Builder setScopeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      scope_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object owner_ = "";
    /**
     * <code>string owner = 2;</code>
     */
    public java.lang.String getOwner() {
      java.lang.Object ref = owner_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        owner_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string owner = 2;</code>
     */
    public com.google.protobuf.ByteString
        getOwnerBytes() {
      java.lang.Object ref = owner_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        owner_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string owner = 2;</code>
     */
    public Builder setOwner(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      owner_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string owner = 2;</code>
     */
    public Builder clearOwner() {
      
      owner_ = getDefaultInstance().getOwner();
      onChanged();
      return this;
    }
    /**
     * <code>string owner = 2;</code>
     */
    public Builder setOwnerBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      owner_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object target_ = "";
    /**
     * <code>string target = 3;</code>
     */
    public java.lang.String getTarget() {
      java.lang.Object ref = target_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        target_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string target = 3;</code>
     */
    public com.google.protobuf.ByteString
        getTargetBytes() {
      java.lang.Object ref = target_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        target_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string target = 3;</code>
     */
    public Builder setTarget(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      target_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string target = 3;</code>
     */
    public Builder clearTarget() {
      
      target_ = getDefaultInstance().getTarget();
      onChanged();
      return this;
    }
    /**
     * <code>string target = 3;</code>
     */
    public Builder setTargetBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      target_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object renewer_ = "";
    /**
     * <code>string renewer = 4;</code>
     */
    public java.lang.String getRenewer() {
      java.lang.Object ref = renewer_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        renewer_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string renewer = 4;</code>
     */
    public com.google.protobuf.ByteString
        getRenewerBytes() {
      java.lang.Object ref = renewer_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        renewer_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string renewer = 4;</code>
     */
    public Builder setRenewer(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      renewer_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string renewer = 4;</code>
     */
    public Builder clearRenewer() {
      
      renewer_ = getDefaultInstance().getRenewer();
      onChanged();
      return this;
    }
    /**
     * <code>string renewer = 4;</code>
     */
    public Builder setRenewerBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      renewer_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.google.cloud.broker.protobuf.GetSessionTokenRequest)
  }

  // @@protoc_insertion_point(class_scope:com.google.cloud.broker.protobuf.GetSessionTokenRequest)
  private static final com.google.cloud.broker.protobuf.GetSessionTokenRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.cloud.broker.protobuf.GetSessionTokenRequest();
  }

  public static com.google.cloud.broker.protobuf.GetSessionTokenRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetSessionTokenRequest>
      PARSER = new com.google.protobuf.AbstractParser<GetSessionTokenRequest>() {
    @java.lang.Override
    public GetSessionTokenRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetSessionTokenRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetSessionTokenRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetSessionTokenRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.cloud.broker.protobuf.GetSessionTokenRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

