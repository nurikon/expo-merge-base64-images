import { NativeModule, requireNativeModule } from 'expo';

import { ExpoMergeBase64ImagesModuleEvents } from './ExpoMergeBase64Images.types';

declare class ExpoMergeBase64ImagesModule extends NativeModule<ExpoMergeBase64ImagesModuleEvents> {
  PI: number;
  hello(): string;
  setValueAsync(value: string): Promise<void>;
  mergeBase64Images(base64_1: string, base64_2: string): Promise<string>;
}

// This call loads the native module object from the JSI.
export default requireNativeModule<ExpoMergeBase64ImagesModule>('ExpoMergeBase64Images');