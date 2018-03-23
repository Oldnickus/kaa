/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.kaaproject.kaa.server.verifiers.trustful.config.gen;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class TrustfulAvroConfig extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TrustfulAvroConfig\",\"namespace\":\"org.kaaproject.kaa.server.verifiers.trustful.config.gen\",\"fields\":[]}");

  public static org.apache.avro.Schema getClassSchema() {
    return SCHEMA$;
  }

  /**
   * Creates a new TrustfulAvroConfig RecordBuilder
   */
  public static org.kaaproject.kaa.server.verifiers.trustful.config.gen.TrustfulAvroConfig.Builder newBuilder() {
    return new org.kaaproject.kaa.server.verifiers.trustful.config.gen.TrustfulAvroConfig.Builder();
  }

  /**
   * Creates a new TrustfulAvroConfig RecordBuilder by copying an existing Builder
   */
  public static org.kaaproject.kaa.server.verifiers.trustful.config.gen.TrustfulAvroConfig.Builder newBuilder(org.kaaproject.kaa.server.verifiers.trustful.config.gen.TrustfulAvroConfig.Builder other) {
    return new org.kaaproject.kaa.server.verifiers.trustful.config.gen.TrustfulAvroConfig.Builder(other);
  }

  /**
   * Creates a new TrustfulAvroConfig RecordBuilder by copying an existing TrustfulAvroConfig
   * instance
   */
  public static org.kaaproject.kaa.server.verifiers.trustful.config.gen.TrustfulAvroConfig.Builder newBuilder(org.kaaproject.kaa.server.verifiers.trustful.config.gen.TrustfulAvroConfig other) {
    return new org.kaaproject.kaa.server.verifiers.trustful.config.gen.TrustfulAvroConfig.Builder(other);
  }

  public org.apache.avro.Schema getSchema() {
    return SCHEMA$;
  }

  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
      default:
        throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value = "unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
      default:
        throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * RecordBuilder for TrustfulAvroConfig instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<TrustfulAvroConfig>
      implements org.apache.avro.data.RecordBuilder<TrustfulAvroConfig> {


    /**
     * Creates a new Builder
     */
    private Builder() {
      super(org.kaaproject.kaa.server.verifiers.trustful.config.gen.TrustfulAvroConfig.SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder
     */
    private Builder(org.kaaproject.kaa.server.verifiers.trustful.config.gen.TrustfulAvroConfig.Builder other) {
      super(other);
    }

    /**
     * Creates a Builder by copying an existing TrustfulAvroConfig instance
     */
    private Builder(org.kaaproject.kaa.server.verifiers.trustful.config.gen.TrustfulAvroConfig other) {
      super(org.kaaproject.kaa.server.verifiers.trustful.config.gen.TrustfulAvroConfig.SCHEMA$);
    }

    @Override
    public TrustfulAvroConfig build() {
      try {
        TrustfulAvroConfig record = new TrustfulAvroConfig();
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
