import { NativeModule, requireNativeModule } from 'expo';

declare class ExpoMergeBase64ImagesModule extends NativeModule {
  mergeBase64Images(base64_1: string, base64_2: string): Promise<string>;
}

// This call loads the native module object from the JSI.
export default requireNativeModule<ExpoMergeBase64ImagesModule>('ExpoMergeBase64Images');